package federico29mg.wmmbe.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"User\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email has to have a valid form")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Receipt> receipts = new HashSet<>();

    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
        receipt.setUser(this);
    }
    public void removeReceipt(Receipt receipt) {
        receipts.remove(receipt);
        receipt.setUser(null);
    }
}
