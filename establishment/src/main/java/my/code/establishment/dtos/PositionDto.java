package my.code.establishment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long id;

    private String name;

    private String createdBy;
    private Timestamp createdAt;

    private String lastModifiedBy;
    private Timestamp lastModifiedAt;

    private String deletedBy;
    private Timestamp deletedAt;
}
