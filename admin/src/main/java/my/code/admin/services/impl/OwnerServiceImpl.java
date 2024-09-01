package my.code.admin.services.impl;

import lombok.RequiredArgsConstructor;
import my.code.admin.producers.OwnerProducer;
import my.code.admin.services.OwnersService;
import my.code.common.dtos.RegisterOwnerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnersService {

    @Value("${spring.rabbitmq.ownerQueues.responseOwner.name}")
    private String responseQueueName;

    private final OwnerProducer ownerProducer;

    private final RabbitTemplate rabbitTemplate;


    @Override
    public Long register(RegisterOwnerDto registerOwnerDto) {
        ownerProducer.registerOwner(registerOwnerDto);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return (Long) rabbitTemplate.receiveAndConvert(responseQueueName);

    }

    @Override
    public Long addEstablishment(Long ownerId, Long establishmentId) {
        HashMap<String, Long> ids = new HashMap<>();

        ids.put("OwnerId", ownerId);
        ids.put("EstablishmentId", establishmentId);

        ownerProducer.addEstablishmentToOwner(ids);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return (Long) rabbitTemplate.receiveAndConvert(responseQueueName);
    }
}
