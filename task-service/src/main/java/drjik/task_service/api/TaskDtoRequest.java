package drjik.task_service.api;

public record TaskDtoRequest (
        Long userId,
        String title,
        String description
) {
}
