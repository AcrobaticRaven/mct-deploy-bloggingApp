package org.example.BloggingPlatformApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentText;
    private LocalDateTime commentedTime;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    Post commentedPost;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    User commentedUser;

}
