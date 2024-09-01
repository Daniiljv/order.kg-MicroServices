package my.code.establishment.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.code.common.dtos.CommonEstablishmentDto;
import my.code.common.dtos.CreateEstablishmentDto;
import my.code.common.dtos.EstablishmentRequestDto;
import my.code.common.dtos.EstablishmentResponseDto;
import my.code.common.enums.EstablishmentRequestType;
import my.code.establishment.entities.Establishment;
import my.code.establishment.mappers.EstablishmentMapper;
import my.code.establishment.producers.EstablishmentProducer;
import my.code.establishment.services.EstablishmentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstablishmentConsumer {

    private final EstablishmentProducer establishmentProducer;

    private final EstablishmentService establishmentService;

    private final EstablishmentMapper establishmentMapper;

    @RabbitListener(queues = "${spring.rabbitmq.queues.requestEstablishment.name}")
    public void executeRequest(EstablishmentRequestDto<?> establishmentRequestDto) {

        EstablishmentRequestType requestType = establishmentRequestDto.getEstablishmentRequestType();

        switch (requestType) {

            case GET_BY_ID -> {
                if(establishmentRequestDto.getData() instanceof Long id) {
                    Establishment establishment = establishmentService.findActiveById(id);
                    EstablishmentResponseDto<CommonEstablishmentDto> responseDto = new EstablishmentResponseDto<>();
                    responseDto.setData(establishmentMapper.toCommonEstablishmentDto(establishment));
                    establishmentProducer.sendEstablishment(responseDto);
                }
            }

            case GET_ALL_ACTIVE -> {
                List<Establishment> allActive = establishmentService.tempFindAllActive();
                EstablishmentResponseDto<List<CommonEstablishmentDto>> responseDto = new EstablishmentResponseDto<>();
                responseDto.setData(
                        allActive.stream()
                                .map(establishmentMapper::toCommonEstablishmentDto)
                                .toList()
                );
                establishmentProducer.sendAllActiveEstablishments(responseDto);
            }

            case CREATE -> {

                if (establishmentRequestDto.getData() instanceof LinkedHashMap) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    CreateEstablishmentDto establishmentDto = objectMapper.convertValue(establishmentRequestDto.getData(), CreateEstablishmentDto.class);

                    Long establishmentId = establishmentService.create(
                            my.code.establishment.dtos.CreateEstablishmentDto.builder()
                                    .name(establishmentDto.getName())
                                    .location(establishmentDto.getAddress())
                                    .ownerId(establishmentDto.getOwnerId())
                                    .build()
                    );

                    establishmentProducer.sendCreatedEstablishmentId(establishmentId);
                }
            }

            default -> log.error("Unexpected request type {}", requestType);
        }
    }
}
