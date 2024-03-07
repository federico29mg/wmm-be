package federico29mg.wmmbe.Repositories;

import federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptStats;
import federico29mg.wmmbe.Entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    @Query("select new federico29mg.wmmbe.DTOs.ReceiptDTOs.ReceiptStats(count(*), sum(r.cost), avg(r.cost)) from Receipt r where r.user.id = :user_uuid")
    ReceiptStats getReceiptStats(@Param("user_uuid") UUID user_uuid);

}
