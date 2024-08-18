package my.code.establishment.producers;


import lombok.RequiredArgsConstructor;
import my.code.common.dtos.CommonPositionDto;
import my.code.establishment.dtos.PositionDto;
import my.code.establishment.mappers.PositionMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionsProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.responsePositions.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;
    private final PositionMapper positionMapper;

    public void sendPositions(List<PositionDto> positions) {
        List<CommonPositionDto> result = positionMapper.fromDtoToCommonPositionsDto(positions);

        rabbitTemplate.convertAndSend(exchange, routingKey, result);
    }
}
