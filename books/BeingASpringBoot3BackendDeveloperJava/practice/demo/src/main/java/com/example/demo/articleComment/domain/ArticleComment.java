package com.example.demo.articleComment.domain;

import com.example.demo.config.AuditingFields;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ArticleComment extends AuditingFields {

    @Id @GeneratedValue
    private Long id;

    private String title;

}
