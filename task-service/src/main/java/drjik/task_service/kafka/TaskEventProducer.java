package drjik.task_service.kafka;

import drjik.common_libs.api.kafka.TaskEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskEventProducer {
    private static final String TOPIC = "task-events";

    private final KafkaTemplate<String, TaskEvent> kafkaTemplate;

    public void send(TaskEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
