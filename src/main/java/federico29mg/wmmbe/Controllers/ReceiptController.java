package federico29mg.wmmbe.Controllers;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.*;
import federico29mg.wmmbe.Services.ReceiptService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ReceiptResponse postReceipt(@Valid @RequestBody NewReceiptRequest newReceiptRequest) {
        return receiptService.postReceipt(newReceiptRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ReceiptResponse> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/user/{user_uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Set<UserReceiptsResponse> getUserReceipts(@NotNull @PathVariable UUID user_uuid) {
        return receiptService.getUserReceipts(user_uuid);
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ReceiptResponse patchReceiptDetails(@PathVariable UUID uuid, @Valid @RequestBody PatchReceiptRequest patchReceiptRequest) {
        return receiptService.patchReceiptDetails(uuid, patchReceiptRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReceipt(@NotNull @PathVariable UUID uuid) {
        receiptService.deleteReceipt(uuid);
    }

    @GetMapping("/user/{user_uuid}/stats")
    @ResponseStatus(HttpStatus.OK)
    public ReceiptStats getReceiptsStats(@NotNull @PathVariable UUID user_uuid) {
        return receiptService.getReceiptStats(user_uuid);
    }
}
