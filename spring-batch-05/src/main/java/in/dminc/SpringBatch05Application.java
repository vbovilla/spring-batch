package in.dminc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatch05Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch05Application.class, args);
    }

}
