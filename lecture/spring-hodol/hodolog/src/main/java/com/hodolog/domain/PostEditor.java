package com.hodolog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {
    // PostEditor 를 통해 수정해야 할 필드를 좁힐 수 있는 큰 장점이 있습니다.

    private final String title; // this.title = title != null ? title; 생성자 안에서 널체크 이렇게도 가능해요!
    private final String content;

    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // ---------------------------------------------------------------------------------

    public static PostEditorBuilder builder() {
        return new PostEditorBuilder();
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public static class PostEditorBuilder {
        private String title;
        private String content;

        public static PostEditorBuilder builder() {
            return new PostEditorBuilder();
        }

        PostEditorBuilder() {
        }

        public PostEditorBuilder title(final String title) {
            if (title != null) {
                this.title = title;
            }
            return this;
        }

        public PostEditorBuilder content(final String content) {
            if (content != null) {
                this.content = content;
            }
            return this;
        }

        public PostEditor build() {
            return new PostEditor(this.title, this.content);
        }

        public String toString() {
            return "PostEditor.PostEditorBuilder(title=" + this.title + ", content=" + this.content + ")";
        }
    }
}
