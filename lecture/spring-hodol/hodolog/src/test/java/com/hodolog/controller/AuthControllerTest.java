package com.hodolog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodolog.domain.Session;
import com.hodolog.domain.User;
import com.hodolog.exception.InvalidSignInInformation;
import com.hodolog.repository.PostRepository;
import com.hodolog.repository.SessionRepository;
import com.hodolog.repository.UserRepository;
import com.hodolog.request.Login;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void setup() {

        // mockMvc 설정
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void test() throws Exception {
        // given
//        userRepository.save(User.builder() // 이미 생성됨
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .password("1234")
//                .build());

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);// 자바 빈 규약에 따라 JSON 으로 변경합니다.

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        // given

        // when
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공 후 세션 1개 생성")
    void test2() throws Exception {
        // given
        User user = userRepository.findByEmailAndPassword("hodolman88@gmail.com", "1234")
                .orElseThrow(InvalidSignInInformation::new);

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);// 자바 빈 규약에 따라 JSON 으로 변경합니다.

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, user.getSessions().size());
    }

    @Test
    @DisplayName("로그인 성공 후 accessToken 응답")
    void test3() throws Exception {
        // given
        User user = userRepository.findByEmailAndPassword("hodolman88@gmail.com", "1234")
                .orElseThrow(InvalidSignInInformation::new);

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);// 자바 빈 규약에 따라 JSON 으로 변경합니다.

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()))
                .andDo(print());

    }
}