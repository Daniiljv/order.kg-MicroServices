package my.code.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonEstablishmentDto {
    private Long id;

    private String name;

    private String location;

    private Long ownerId;

    private List<CommonPositionDto> positions = new ArrayList<>();

    private String createdBy;
    private Timestamp createdAt;

    private String lastModifiedBy;
    private Timestamp lastModifiedAt;

    private String deletedBy;
    private Timestamp deletedAt;
}
