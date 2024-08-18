package my.code.establishment.producers;

import lombok.RequiredArgsConstructor;

import my.code.common.dtos.CommonEstablishmentDto;
import my.code.common.dtos.EstablishmentRequestDto;
import my.code.common.dtos.EstablishmentResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstablishmentProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.responseEstablishment.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;


    public void sendEstablishment(EstablishmentResponseDto<CommonEstablishmentDto> responseDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, responseDto);
    }

    public void sendAllActiveEstablishments(EstablishmentResponseDto<List<CommonEstablishmentDto>> requestDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, requestDto);
    }
}
