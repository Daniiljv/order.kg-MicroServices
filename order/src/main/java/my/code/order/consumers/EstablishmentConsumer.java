package my.code.order.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.code.common.dtos.CommonEstablishmentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EstablishmentConsumer {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @RabbitListener(queues = "${spring.rabbitmq.queues.response1.name}")
    public void getDto(String gottenEstablishment) throws JsonProcessingException {

        CommonEstablishmentDto result = objectMapper.readValue(gottenEstablishment, CommonEstablishmentDto.class);
        System.out.println(result);

    }
}
