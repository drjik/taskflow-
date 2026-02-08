package drjik.task_service.domain;

import drjik.task_service.api.TaskChangeStatusRequest;
import drjik.task_service.api.TaskDtoRequest;
import drjik.task_service.api.TaskDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskJpaRepository taskRepository;

    private final TaskEntityMapper taskEntityMapper;

    public List<TaskDtoResponse> findAllByUserId(Long userId) {
       List<TaskEntity> tasks = taskRepository.findAllByUserId(userId);

       return tasks.stream().map(taskEntityMapper::toResponse).toList();
    }

    public TaskDtoResponse changeStatus(Long id, TaskChangeStatusRequest request) {

        TaskEntity task = taskRepository.findById(id).get();
        task.setStatus(request.status());

        taskRepository.save(task);

        return taskEntityMapper.toResponse(task);
    }

    public TaskDtoResponse createTask(TaskDtoRequest taskDto){

        TaskEntity taskEntity = taskEntityMapper.toEntity(taskDto);

        taskEntity.setStatus(Status.NEW);
        taskEntity.setCreatedAt(LocalDateTime.now());

        return taskEntityMapper.toResponse(taskRepository.save(taskEntity));
    }
}
