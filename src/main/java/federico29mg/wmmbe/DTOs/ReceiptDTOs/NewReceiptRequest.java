package federico29mg.wmmbe.DTOs.ReceiptDTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NewReceiptRequest {
    private UUID user_id;

    @NotEmpty(message = "Cost is mandatory")
    private String place;

    private String detail;

    @NotNull(message = "Cost is mandatory")
    private Double cost;
}
