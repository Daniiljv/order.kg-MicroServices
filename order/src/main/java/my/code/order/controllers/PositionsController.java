package my.code.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.CommonPositionDto;
import my.code.order.producers.PositionsProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionsController {

    private final PositionsProducer positionsProducer;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queues.responsePositions.name}")
    private String responseQueueName;

    @GetMapping("/findAllByEstablishment/{establishmentId}")
    public List<CommonPositionDto> findAllByEstablishmentId(@PathVariable Long establishmentId) {
        positionsProducer.findAllByEstablishmentId(establishmentId);

        @SuppressWarnings("unchecked")
       List<CommonPositionDto> result =
                (List<CommonPositionDto>) rabbitTemplate.receiveAndConvert(responseQueueName);

        return result;
    }
}
