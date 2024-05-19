package in.dminc.config;

import in.dminc.model.StudentJdbc;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    JdbcCursorItemReader<StudentJdbc> studentJdbcJdbcCursorItemReader;

    @Autowired
    private FlatFileItemWriter studentJdbcFlatFileItemWriter;

    @Bean
    public Job jdbcBatchToFlatFileItemJob() {
        return jobBuilderFactory.get("JDBC Batch Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcbatchItemStep()).build();
    }

    public Step jdbcbatchItemStep() {
        return stepBuilderFactory.get("JDBC Batch Item Step")
            .<StudentJdbc, StudentJdbc>chunk(100)
            .reader(studentJdbcJdbcCursorItemReader)
            .writer(studentJdbcFlatFileItemWriter)
            .build();
    }
}
