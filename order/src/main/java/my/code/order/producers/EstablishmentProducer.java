package my.code.order.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.CommonEstablishmentDto;
import my.code.common.dtos.EstablishmentRequestDto;
import my.code.common.enums.EstablishmentRequestType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstablishmentProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.request1.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void findById(Long id) {

        EstablishmentRequestDto<Long> sendEstablishmentId = new EstablishmentRequestDto<>();
        sendEstablishmentId.setEstablishmentRequestType(EstablishmentRequestType.GET_BY_ID);
        sendEstablishmentId.setData(id);

        rabbitTemplate.convertAndSend(exchange, routingKey, sendEstablishmentId);
    }

    public void findAllActive(){
        EstablishmentRequestDto<List<CommonEstablishmentDto>> allActiveEstablishments =
                new EstablishmentRequestDto<>();

        rabbitTemplate.convertAndSend(exchange, routingKey, allActiveEstablishments);
    }

}
