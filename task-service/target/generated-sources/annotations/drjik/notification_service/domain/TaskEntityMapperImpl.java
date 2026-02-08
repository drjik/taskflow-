package drjik.notification_service.domain;

import drjik.task_service.api.TaskDtoRequest;
import drjik.task_service.api.TaskDtoResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

import drjik.task_service.domain.Status;
import drjik.task_service.domain.TaskEntity;
import drjik.task_service.domain.TaskEntityMapper;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-05T18:35:27+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class TaskEntityMapperImpl implements TaskEntityMapper {

    @Override
    public TaskEntity toEntity(TaskDtoRequest request) {
        if ( request == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setUserId( request.userId() );
        taskEntity.setTitle( request.title() );
        taskEntity.setDescription( request.description() );

        return taskEntity;
    }

    @Override
    public TaskDtoResponse toResponse(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        Status status = null;
        LocalDateTime createdAt = null;

        id = taskEntity.getId();
        title = taskEntity.getTitle();
        description = taskEntity.getDescription();
        status = taskEntity.getStatus();
        createdAt = taskEntity.getCreatedAt();

        Long user_id = null;

        TaskDtoResponse taskDtoResponse = new TaskDtoResponse( id, user_id, title, description, status, createdAt );

        return taskDtoResponse;
    }
}
