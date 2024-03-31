package com.hodolog.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob // 자바에서는 String, DB 에서는 Long 타입으로
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .content(content); // 빌더 클래스 자체를 전달
    }

    public void edit(PostEditor postEditor) { // 픽스된 포스트 에디터
        title = postEditor.getTitle();
        content = postEditor.getContent();
    }
}
