package limdae.spblog.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)  private String title;
    @Column(name = "content", nullable = false) private String content;
    @CreatedDate @Column(name = "created_at") private LocalDateTime createAt;
    @LastModifiedDate @Column(name = "updated_at") private LocalDateTime updatedAt;

    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
