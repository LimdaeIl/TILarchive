package com.fastcampus.projectboardadmin.repository;

import com.fastcampus.projectboardadmin.domain.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
class JpaRepositoryTest {

    private final UserAccountRepository userAccountRepository;

    /**
     * @param userAccountRepository 생성자 주입 방식
     */
    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    public void givenUserAccounts_whenSelecting_thenWorksFine() throws Exception {
        // given


        // when
        List<UserAccount> userAccounts = userAccountRepository.findAll();


        // then
        assertThat(userAccounts).isNotNull()
                .isNotNull()
                .hasSize(4);
    }
}
