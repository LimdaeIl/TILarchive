package fastcampus.practice.config;

import fastcampus.practice.service.DemoService;
import fastcampus.practice.service.SampleTest;
import fastcampus.practice.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DemoConfig {

    @Bean
    @Primary
    public DemoService demoService() {
        return new DemoService();
    }

    @Bean
    public Service sampleService() {
        return new SampleTest();
    }
}
