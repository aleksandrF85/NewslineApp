package com.example.Newsline.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String fullName;

    @Column(name="registered")
    @CreationTimestamp
    private LocalDateTime registeredAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsAuthor")
    @Builder.Default
    private List<News> news = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentAuthor")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

}
