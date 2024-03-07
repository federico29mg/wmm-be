package federico29mg.wmmbe.Controllers;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptRequest;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptResponse;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptStats;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.UserReceiptsResponse;
import federico29mg.wmmbe.Services.ReceiptService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewReceiptResponse postReceipt(@Valid @RequestBody NewReceiptRequest newReceiptRequest) {
        return receiptService.postReceipt(newReceiptRequest);
    }
    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReceipt(@NotNull @PathVariable UUID uuid) {
        receiptService.deleteReceipt(uuid);
    }
    @GetMapping("/user/{user_uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserReceiptsResponse> getReceipts(@NotNull @PathVariable UUID user_uuid) {
        return receiptService.getReceipts(user_uuid);
    }
    @GetMapping("/stats/user/{user_uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ReceiptStats getReceiptsStats(@NotNull @PathVariable UUID user_uuid) {
        return receiptService.getReceiptStats(user_uuid);
    }
}
