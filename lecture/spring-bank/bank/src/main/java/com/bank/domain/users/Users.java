package com.bank.domain.users;

import java.time.LocalDateTime;

public class Users {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private UserEnum role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
