package my.code.order.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.PositionRequestDto;
import my.code.common.enums.PositionRequestType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionsProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.requestPositions.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void findAllByEstablishmentId(Long establishmentId) {
        PositionRequestDto<Long> requestDto = new PositionRequestDto<>();
        requestDto.setPositionRequestType(PositionRequestType.GET_ALL_BY_ESTABLISHMENT_ID);
        requestDto.setData(establishmentId);

        rabbitTemplate.convertAndSend(exchange, routingKey, requestDto);
    }
}
