package com.hodolog.service;

import com.hodolog.crypto.PasswordEncoder;
import com.hodolog.domain.User;
import com.hodolog.exception.AlreadyExistsEmailException;
import com.hodolog.repository.UserRepository;
import com.hodolog.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Signup signup) {
        Optional<User> byEmail = userRepository.findByEmail(signup.getEmail());
        if (byEmail.isPresent()) {
            throw new AlreadyExistsEmailException();
        }


        String encryptedPassword = passwordEncoder.encrypt(signup.getPassword());

        User user = User.builder()
                .name(signup.getName())
                .email(signup.getEmail())
                .password(encryptedPassword)
                .build();
        userRepository.save(user);
    }
}
