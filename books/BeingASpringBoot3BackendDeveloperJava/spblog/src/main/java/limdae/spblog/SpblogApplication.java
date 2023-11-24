package limdae.spblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpblogApplication.class, args);
	}

}
