package my.code.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.CommonEstablishmentDto;
import my.code.common.dtos.EstablishmentResponseDto;
import my.code.order.producers.EstablishmentProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentsController {

    private final RabbitTemplate rabbitTemplate;
    private final EstablishmentProducer establishmentProducer;

    @Value("${spring.rabbitmq.queues.response1.name}")
    private String queueName;


    @GetMapping("/{id}")
    public EstablishmentResponseDto<CommonEstablishmentDto> test(@PathVariable Long id) {

        establishmentProducer.findById(id);

        @SuppressWarnings("unchecked")
        EstablishmentResponseDto<CommonEstablishmentDto> gottenEstablishment =
                (EstablishmentResponseDto<CommonEstablishmentDto>) rabbitTemplate.receiveAndConvert(queueName);

        System.out.println(gottenEstablishment);

        return gottenEstablishment;
    }

    @GetMapping
    public List<CommonEstablishmentDto> findAll() {

        establishmentProducer.findAllActive();

        @SuppressWarnings("unchecked")
                EstablishmentResponseDto<List<CommonEstablishmentDto>> gottenEstablishments =
                (EstablishmentResponseDto<List<CommonEstablishmentDto>>) rabbitTemplate.receiveAndConvert(queueName);

        if(gottenEstablishments == null) {
            return new ArrayList<>();
        }

        return gottenEstablishments.getData();
    }
}
