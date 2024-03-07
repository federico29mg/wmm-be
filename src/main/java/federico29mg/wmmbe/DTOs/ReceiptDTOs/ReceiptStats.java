package federico29mg.wmmbe.DTOs.ReceiptDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptStats {
    private Long receipts_count;
    private Double receipts_sum;
    private Double receipts_average;
}
