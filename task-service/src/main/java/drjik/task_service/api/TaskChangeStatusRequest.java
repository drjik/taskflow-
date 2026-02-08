package drjik.task_service.api;

import drjik.task_service.domain.Status;

public record TaskChangeStatusRequest(
        Status status
) {
}
