package federico29mg.wmmbe.DTOs.ReceiptDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserReceiptsResponse {
    private UUID id;
    private LocalDate date;
    private String place;
    private String detail;
    private Double cost;
}
