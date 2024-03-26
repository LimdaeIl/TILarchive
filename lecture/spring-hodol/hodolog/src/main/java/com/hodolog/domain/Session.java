package com.hodolog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessToken;

    @ManyToOne
    private User user;

    @Builder
    public Session(String accessToken, User user) {
        this.accessToken = randomUUID().toString();
        this.user = user;
    }
}
