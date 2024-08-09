package com.example.Newsline.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_author", nullable = false)
    private User commentAuthor;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @CreationTimestamp
    private Instant createdAt;

//    @UpdateTimestamp
//    private Instant updatedAt;
}
