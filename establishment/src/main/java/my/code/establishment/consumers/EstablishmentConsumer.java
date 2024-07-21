package my.code.establishment.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.establishment.entities.Establishment;
import my.code.establishment.mappers.EstablishmentMapper;
import my.code.establishment.producers.EstablishmentProducer;
import my.code.establishment.services.EstablishmentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstablishmentConsumer {

    private final EstablishmentProducer establishmentProducer;

    private final EstablishmentService establishmentService;

    private final EstablishmentMapper establishmentMapper;

    @RabbitListener(queues = "${spring.rabbitmq.queues.queue1.name}")
    public void findById(Long id) {
        log.info("Received ID: {}", id);
        Establishment establishment = establishmentService.findActiveById(id);
        System.out.println(establishmentMapper.toDto(establishment));
        establishmentProducer.sendEstablishment(establishment);
    }
}
