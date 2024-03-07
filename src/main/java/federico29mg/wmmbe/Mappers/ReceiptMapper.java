package federico29mg.wmmbe.Mappers;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptRequest;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptResponse;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.UserReceiptsResponse;
import federico29mg.wmmbe.Entities.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
    NewReceiptResponse receiptToNewReceiptResponse(Receipt receipt);

    UserReceiptsResponse receiptToUserReceiptsResponse(Receipt receipt);
    Set<UserReceiptsResponse> receiptSetToUserReceiptsResponseSet(Set<Receipt> receipts);
}
