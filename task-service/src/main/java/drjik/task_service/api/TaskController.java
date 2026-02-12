package drjik.task_service.api;

import drjik.task_service.domain.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<TaskDtoResponse> getTasksByUserId(@RequestParam Long userId) {
        return taskService.findAllByUserId(userId);
    }

    @PostMapping()
    public TaskDtoResponse createTask(@RequestBody TaskDtoRequest taskEntityDto) {
        return taskService.createTask(taskEntityDto);
    }

    @PutMapping("/{id}/status")
    public TaskDtoResponse updateTaskStatus(@PathVariable Long id, @RequestBody TaskChangeStatusRequest request) {
        return taskService.changeStatus(id, request);
    }
}
