package drjik.task_service.api;

import drjik.task_service.domain.Status;

import java.time.LocalDateTime;

public record TaskDtoResponse (
        Long id,
        Long user_id,
        String title,
        String description,
        Status status,
        LocalDateTime createdAt) {
}