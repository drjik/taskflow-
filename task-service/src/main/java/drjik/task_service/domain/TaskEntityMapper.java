package drjik.task_service.domain;

import drjik.task_service.api.TaskDtoRequest;
import drjik.task_service.api.TaskDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskEntityMapper {
    TaskEntity toEntity(TaskDtoRequest request);

    TaskDtoResponse toResponse(TaskEntity taskEntity);
}