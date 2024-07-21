package my.code.establishment.producers;

import lombok.RequiredArgsConstructor;
import my.code.establishment.dtos.EstablishmentDto;
import my.code.establishment.entities.Establishment;
import my.code.establishment.mappers.EstablishmentMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.queue2.json.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    private final EstablishmentMapper establishmentMapper;

    public void sendEstablishment(Establishment establishment) {


        rabbitTemplate.convertAndSend(exchange, routingKey, establishmentMapper.toCommonEstablishmentDto(establishment));
    }
}
