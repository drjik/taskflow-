package drjik.task_service.domain;

import drjik.common_libs.api.kafka.TaskEvent;
import drjik.task_service.api.TaskChangeStatusRequest;
import drjik.task_service.api.TaskDtoRequest;
import drjik.task_service.api.TaskDtoResponse;
import drjik.task_service.kafka.TaskEventProducer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskJpaRepository taskRepository;
    private final TaskEntityMapper taskEntityMapper;
    private final TaskEventProducer taskEventProducer;

    public List<TaskDtoResponse> findAllByUserId(Long userId) {
       List<TaskEntity> tasks = taskRepository.findAllByUserId(userId);

       return tasks.stream().map(taskEntityMapper::toResponse).toList();
    }

    @Transactional
    public TaskDtoResponse changeStatus(Long id, TaskChangeStatusRequest request) {

        TaskEntity task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        task.setStatus(request.status());

        TaskEntity newTask = taskRepository.save(task);

        taskEventProducer.send(
                new TaskEvent(
                        "TASK_STATUS_CHANGED",
                        newTask.getId(),
                        newTask.getUserId(),
                        newTask.getStatus().name(),
                        LocalDateTime.now()
                )
        );

        System.out.println("Task status changed to " + newTask.getStatus().name());

        return taskEntityMapper.toResponse(newTask);
    }

    @Transactional
    public TaskDtoResponse createTask(TaskDtoRequest taskDto){

        TaskEntity taskEntity = taskEntityMapper.toEntity(taskDto);

        taskEntity.setStatus(Status.NEW);
        taskEntity.setCreatedAt(LocalDateTime.now());

        TaskEntity newTask = taskRepository.save(taskEntity);

        taskEventProducer.send(
                new TaskEvent(
                        "TASK_CREATED",
                        newTask.getId(),
                        newTask.getUserId(),
                        newTask.getStatus().name(),
                        LocalDateTime.now()
                )
        );

        System.out.println("Task created");

        return taskEntityMapper.toResponse(newTask);
    }
}
