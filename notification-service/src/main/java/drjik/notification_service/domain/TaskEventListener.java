package drjik.notification_service.domain;

import drjik.common_libs.api.kafka.TaskEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableKafka
@Component
@AllArgsConstructor
public class TaskEventListener {
    @KafkaListener(
            topics = "task-events",
            containerFactory = "taskEventListenerFactory"
    )
    public void listen(TaskEvent event) {
        log.info("Received task event {}", event);
    }
}
