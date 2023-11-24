package limdae.spblog.springbootdeveloper.repository;


import limdae.spblog.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
