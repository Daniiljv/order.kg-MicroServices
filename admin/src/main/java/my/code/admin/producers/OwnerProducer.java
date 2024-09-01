package my.code.admin.producers;

import lombok.RequiredArgsConstructor;
import my.code.common.dtos.OwnerRequestDto;
import my.code.common.dtos.RegisterOwnerDto;
import my.code.common.enums.OwnerRequestType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OwnerProducer {

    @Value("${spring.rabbitmq.ownerExchange.name}")
    private String ownerExchangeName;

    @Value("${spring.rabbitmq.ownerQueues.requestOwner.routing-key}")
    private String requestOwnerRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void registerOwner(RegisterOwnerDto registerOwnerDto) {

        OwnerRequestDto<RegisterOwnerDto> ownerRequestDto = new OwnerRequestDto<>();
        ownerRequestDto.setOwnerRequestType(OwnerRequestType.REGISTER);
        ownerRequestDto.setData(registerOwnerDto);

        rabbitTemplate.convertAndSend(ownerExchangeName, requestOwnerRoutingKey, ownerRequestDto);
    }

    public void addEstablishmentToOwner(HashMap<String, Long> ids){

        OwnerRequestDto<HashMap<String, Long>> ownerRequestDto = new OwnerRequestDto<>();
        ownerRequestDto.setOwnerRequestType(OwnerRequestType.ADD_ESTABLISHMENT);
        ownerRequestDto.setData(ids);

        rabbitTemplate.convertAndSend(ownerExchangeName, requestOwnerRoutingKey, ownerRequestDto);
    }
}
