package my.code.establishment.consumers;

import lombok.RequiredArgsConstructor;
import my.code.common.dtos.PositionRequestDto;
import my.code.establishment.dtos.PositionDto;
import my.code.establishment.producers.PositionsProducer;
import my.code.establishment.services.PositionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionsConsumer {

    private final PositionService positionService;
    private final PositionsProducer positionsProducer;


    @RabbitListener(queues = "${spring.rabbitmq.queues.requestPositions.name}")
    public void findPositionsByEstablishmentId(PositionRequestDto<Long> positionRequestDto) {

        List<PositionDto> positions = positionService.findByEstablishmentId(positionRequestDto.getData());

        positionsProducer.sendPositions(positions);
    }
}
