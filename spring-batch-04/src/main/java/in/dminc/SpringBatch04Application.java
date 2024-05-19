package in.dminc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatch04Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch04Application.class, args);
    }

}
