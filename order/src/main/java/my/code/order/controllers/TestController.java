package my.code.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.CommonEstablishmentDto;
import my.code.order.producers.EstablishmentProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;
    private final EstablishmentProducer establishmentProducer;

    @Value("${spring.rabbitmq.queues.queue2.json.name}")
    private String queueName;


    @GetMapping("/{id}")
    public CommonEstablishmentDto test(@PathVariable Long id) {

        establishmentProducer.sendMessage(id);

        CommonEstablishmentDto gottenEstablishment = (CommonEstablishmentDto) rabbitTemplate.receiveAndConvert(queueName);

        System.out.println(gottenEstablishment);

        return gottenEstablishment;

    }

}
