package federico29mg.wmmbe.Mappers;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptRequest;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptResponse;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.UserReceiptsResponse;
import federico29mg.wmmbe.Entities.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper
public interface ReceiptMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "date", ignore = true),
            @Mapping(source = "user_id", target = "user.id")
    })
    Receipt newReceiptRequestToReceipt(NewReceiptRequest newReceiptRequest);
    @Mappings({
            @Mapping(source = "user.id", target = "user_id")
    })
    ReceiptResponse receiptToReceiptResponse(Receipt receipt);
    List<ReceiptResponse> receiptListToReceiptResponseList(List<Receipt> receipts);
    UserReceiptsResponse receiptToUserReceiptsResponse(Receipt receipt);
    Set<UserReceiptsResponse> receiptSetToUserReceiptsResponseSet(Set<Receipt> receipts);
}
