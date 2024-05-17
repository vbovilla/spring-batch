package in.dminc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

// @EnableBatchProcessing enables Spring Batch functionality on application
@EnableBatchProcessing

// @EnableAsync adds async functionality to application.
// To make any particular method run asynchronously, annotate corresponding method with @Async
@EnableAsync

// @EnableScheduling enables scheduling functionality on the application
@EnableScheduling

public class SpringBatch02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch02Application.class, args);
    }

}
