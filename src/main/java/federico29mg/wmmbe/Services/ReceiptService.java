package federico29mg.wmmbe.Services;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptRequest;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptResponse;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptStats;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.UserReceiptsResponse;
import federico29mg.wmmbe.Entities.Receipt;
import federico29mg.wmmbe.Exceptions.ReceiptExceptions.ReceiptNotFoundException;
import federico29mg.wmmbe.Mappers.ReceiptMapper;
import federico29mg.wmmbe.Repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final UserService userService;
    public ReceiptService(ReceiptRepository receiptRepository, ReceiptMapper receiptMapper, UserService userService) {
        this.receiptRepository = receiptRepository;
        this.receiptMapper = receiptMapper;
        this.userService = userService;
    }

    public NewReceiptResponse postReceipt(NewReceiptRequest newReceiptRequest) {
        Receipt receipt = receiptMapper.newReceiptRequestToReceipt(newReceiptRequest);
        receipt.setDate(LocalDate.now());
        receipt = userService.saveUserReceipt(receipt);
        return receiptMapper.receiptToNewReceiptResponse(receipt);
    }
    public void deleteReceipt(UUID uuid) {
        userService.deleteUserReceipt(getReceiptById(uuid));
    }
    public Set<UserReceiptsResponse> getReceipts(UUID user_uuid) {
        Set<Receipt> receipts = userService.getUserReceipts(user_uuid);
        return receiptMapper.receiptSetToUserReceiptsResponseSet(receipts);
    }
    public ReceiptStats getReceiptStats(UUID user_uuid) {
        return receiptRepository.getReceiptStats(user_uuid);
    }

    private Receipt getReceiptById(UUID uuid) {
        Optional<Receipt> receipt = receiptRepository.findById(uuid);
        if(receipt.isEmpty()) {
            throw new ReceiptNotFoundException("Receipt not found");
        }
        return receipt.get();
    }
}
