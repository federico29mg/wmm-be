package federico29mg.wmmbe.DTOs.ReceiptDTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptResponse {
    private UUID id;
    private UUID user_id;
    private LocalDate date;
    private String place;
    private String detail;
    private Double cost;
}
