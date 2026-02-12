package drjik.common_libs.api.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEvent {
    private String eventType;
    private Long taskId;
    private Long userId;
    private String status;
    private LocalDateTime timestamp;
}