package org.example.BloggingPlatformApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Enumerated(EnumType.STRING)
    private TOPIC postTopic;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    User postOwner;

}
