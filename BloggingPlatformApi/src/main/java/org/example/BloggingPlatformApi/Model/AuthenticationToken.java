package org.example.BloggingPlatformApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;
    private String tokenValue;
    private LocalDateTime authCreationStamp;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User authUser;

    public AuthenticationToken(User connectedUser) {
        this.authUser = connectedUser;
        this.tokenValue = UUID.randomUUID().toString();
        this.authCreationStamp = LocalDateTime.now();
    }
}
