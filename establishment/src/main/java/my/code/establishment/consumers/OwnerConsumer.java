package my.code.establishment.consumers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.OwnerRequestDto;
import my.code.common.dtos.RegisterOwnerDto;
import my.code.common.enums.OwnerRequestType;
import my.code.establishment.producers.OwnerProducer;
import my.code.establishment.services.OwnersService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerConsumer {

    private final OwnersService ownersService;
    private final OwnerProducer ownerProducer;

    @RabbitListener(queues = "${spring.rabbitmq.ownerQueues.requestOwner.name}")
    public void executeRequest(OwnerRequestDto<?> requestDto) {

        OwnerRequestType requestType = requestDto.getOwnerRequestType();

        switch (requestType) {

            case REGISTER -> {

                if(requestDto.getData() instanceof LinkedHashMap){

                    ObjectMapper objectMapper = new ObjectMapper();
                    RegisterOwnerDto registerOwnerDto =
                            objectMapper.convertValue(requestDto.getData(), RegisterOwnerDto.class);

                    Long ownerId = ownersService.register(registerOwnerDto);

                    ownerProducer.sendOwnerId(ownerId);
                }
            }

            case ADD_ESTABLISHMENT -> {

                if(requestDto.getData() instanceof HashMap){

                    ObjectMapper objectMapper = new ObjectMapper();
                    HashMap<String, Long> ids =
                            objectMapper.convertValue(requestDto.getData(),
                                    new TypeReference<>() {
                                    });

                   Long addedEstablishmentId = ownersService.addEstablishment(ids);
                   ownerProducer.sendAddedEstablishmentId(addedEstablishmentId);
                }
            }

        }
    }
}
