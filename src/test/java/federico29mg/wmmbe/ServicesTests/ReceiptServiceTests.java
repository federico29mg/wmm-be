package federico29mg.wmmbe.ServicesTests;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptRequest;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.NewReceiptResponse;
import federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptStats;
import federico29mg.wmmbe.Entities.Receipt;
import federico29mg.wmmbe.Entities.User;
import federico29mg.wmmbe.Exceptions.ReceiptExceptions.ReceiptNotFoundException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UsernameAlreadyExistsException;
import federico29mg.wmmbe.Mappers.ReceiptMapper;
import federico29mg.wmmbe.Repositories.ReceiptRepository;
import federico29mg.wmmbe.Services.ReceiptService;
import federico29mg.wmmbe.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTests {
    @Mock
    private ReceiptRepository receiptRepository;
    @Mock
    private ReceiptMapper receiptMapper;
    @Mock
    private UserService userService;

    @InjectMocks
    private ReceiptService receiptService;

    private NewReceiptRequest newReceiptRequest;
    private Receipt newReceipt;
    private Receipt receipt;
    private NewReceiptResponse newReceiptResponse;

    @BeforeEach
    public void setUp() {
        newReceiptRequest = NewReceiptRequest.builder()
                .user_id(UUID.randomUUID())
                .place("C&W")
                .detail("Dinner with friends")
                .cost(50000.0)
                .build();

        newReceipt = Receipt.builder()
                .user(User.builder().id(newReceiptRequest.getUser_id()).build())
                .place(newReceiptRequest.getPlace())
                .detail(newReceiptRequest.getDetail())
                .cost(newReceiptRequest.getCost())
                .build();

        receipt = Receipt.builder()
                .id(UUID.randomUUID())
                .user(newReceipt.getUser())
                .date(LocalDate.now())
                .place("C&W")
                .detail("Dinner with friends")
                .cost(50000.0)
                .build();

        newReceiptResponse = NewReceiptResponse.builder()
                .id(receipt.getId())
                .user_id(receipt.getUser().getId())
                .date(receipt.getDate())
                .place(receipt.getPlace())
                .detail(receipt.getDetail())
                .cost(receipt.getCost())
                .build();
    }

    @Test
    public void givenNewReceiptRequest_whenSavingNewReceipt_thenReturnNewReceiptResponse() {
        when(receiptMapper.newReceiptRequestToReceipt(newReceiptRequest)).thenReturn(newReceipt);
        when(userService.saveUserReceipt(newReceipt)).thenReturn(receipt);
        when(receiptMapper.receiptToNewReceiptResponse(receipt)).thenReturn(newReceiptResponse);

        NewReceiptResponse testNewReceiptResponse = receiptService.postReceipt(newReceiptRequest);

        verify(receiptMapper, times(1)).newReceiptRequestToReceipt(newReceiptRequest);
        verify(userService, times(1)).saveUserReceipt(newReceipt);
        verify(receiptMapper, times(1)).receiptToNewReceiptResponse(receipt);

        assertThat(testNewReceiptResponse.getId()).isNotNull();
    }

    @Test
    public void givenReceiptUUID_whenDeletingReceipt_thenDeleteReceipt() {
        when(receiptRepository.findById(receipt.getId())).thenReturn(Optional.of(receipt));
        doNothing().when(userService).deleteUserReceipt(receipt);

        receiptService.deleteReceipt(receipt.getId());

        verify(receiptRepository, times(1)).findById(receipt.getId());
        verify(userService, times(1)).deleteUserReceipt(receipt);
    }

    @Test
    public void givenNonExistentReceiptUUID_whenDeletingReceipt_thenThrowReceiptNotFoundException() {
        when(receiptRepository.findById(receipt.getId())).thenReturn(Optional.empty());

        assertThrows(ReceiptNotFoundException.class, () -> {
            receiptService.deleteReceipt(receipt.getId());
        });
    }

    @Test
    public void givenUserUUID_whenGettingReceiptStatsOfUser_thenReturnReceiptStats() {
        UUID user_uuid = UUID.randomUUID();
        when(receiptRepository.getReceiptStats(user_uuid)).thenReturn(new ReceiptStats(1L, 50000.0, 50000.0));

        ReceiptStats testReceiptStats = receiptService.getReceiptStats(user_uuid);

        verify(receiptRepository, times(1)).getReceiptStats(user_uuid);

        assertThat(testReceiptStats.getReceipts_count()).isNotNull();
        assertThat(testReceiptStats.getReceipts_sum()).isNotNull();
        assertThat(testReceiptStats.getReceipts_average()).isNotNull();
    };
}
