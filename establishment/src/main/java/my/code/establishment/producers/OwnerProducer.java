package my.code.establishment.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerProducer {

    @Value("${spring.rabbitmq.ownerExchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.ownerQueues.responseOwner.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendOwnerId(Long ownerId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, ownerId);
    }

    public void sendAddedEstablishmentId(Long establishmentId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, establishmentId);
    }
}
