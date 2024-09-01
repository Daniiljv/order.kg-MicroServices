package my.code.admin.services.impl;

import lombok.RequiredArgsConstructor;
import my.code.admin.dtos.SuccessfullyCreatedEstablishmentDto;
import my.code.admin.producers.EstablishmentProducer;
import my.code.admin.services.EstablishmentService;
import my.code.common.dtos.CreateEstablishmentDto;
import my.code.common.dtos.EstablishmentResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentProducer establishmentProducer;

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queues.responseEstablishment.name}")
    private String responseQueueName;


    @Override
    public SuccessfullyCreatedEstablishmentDto createEstablishment(CreateEstablishmentDto createEstablishmentDto) {
        establishmentProducer.createEstablishment(createEstablishmentDto);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        EstablishmentResponseDto<Long> establishmentResponse =
                (EstablishmentResponseDto<Long>) rabbitTemplate.receiveAndConvert(responseQueueName);

        System.out.println(establishmentResponse);
        System.out.println(establishmentResponse.getData().getClass());

        if (establishmentResponse != null) {
            return new SuccessfullyCreatedEstablishmentDto
                    (establishmentResponse.getData(), createEstablishmentDto.getName());
        }
        else
            return new SuccessfullyCreatedEstablishmentDto();
    }
}
