package my.code.admin.producers;

import lombok.RequiredArgsConstructor;
import my.code.common.dtos.CreateEstablishmentDto;
import my.code.common.dtos.EstablishmentRequestDto;
import my.code.common.enums.EstablishmentRequestType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queues.requestEstablishment.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void createEstablishment(CreateEstablishmentDto establishmentDto) {

        EstablishmentRequestDto<CreateEstablishmentDto> establishmentRequestDto =
                new EstablishmentRequestDto<>();
        establishmentRequestDto.setEstablishmentRequestType(EstablishmentRequestType.CREATE);
        establishmentRequestDto.setData(establishmentDto);

        rabbitTemplate.convertAndSend(exchange, routingKey, establishmentRequestDto);

    }

}
