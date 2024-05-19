package in.dminc.config;

import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import in.dminc.processor.StudentJdbcToJsonProcessor;
import in.dminc.processor.StudentJdbcToXmlProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
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
    JdbcCursorItemReader<StudentJdbc> studentJdbcCursorItemReader;

    @Autowired
    StudentJdbcToJsonProcessor studentJdbcToJsonProcessor;
    @Autowired
    StudentJdbcToXmlProcessor studentJdbcToXmlProcessor;

    @Autowired
    FlatFileItemWriter<StudentJdbc> studentJdbcFlatFileItemWriter;
    @Autowired
    JsonFileItemWriter<StudentJdbc> studentJdbcJsonFileItemWriter;
    @Autowired
    JsonFileItemWriter<StudentJson> studentJdbcToJsonFileItemWriter;
    @Autowired
    StaxEventItemWriter<StudentXml> studentXmlStaxEventItemWriter;
    @Autowired
    JdbcBatchItemWriter<StudentJdbc> studentJdbcJBatchItemWriter;
    @Autowired
    JdbcBatchItemWriter<StudentJdbc> studentJdbcJBatchItemWriterUsingPreparedStatement;
    @Autowired
    ItemWriterAdapter<StudentJdbc> studentJdbcItemWriterAdapter;


    public Job jdbcBatchToFlatFileItemJob() {
        return jobBuilderFactory.get("JDBC Batch to CSV File Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToFlatFileItemStep())
            .build();
    }

    public Job jdbcBatchToJsonFileItemJob() {
        return jobBuilderFactory.get("JDBC Batch to JSON File Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToJsonFileItemStep())
            .build();
    }

    public Job jdbcBatchToJsonFileItemWithProcessorJob() {
        return jobBuilderFactory.get("JDBC Batch to JSON File Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToJsonFileItemWithProcessorStep())
            .build();
    }

    public Job jdbcBatchToXmlFileItemWithProcessorJob() {
        return jobBuilderFactory.get("JDBC Batch to JSON File Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToXmlFileItemWithProcessorStep())
            .build();
    }

    public Job jdbcBatchToJdbcBatchItemJob() {
        return jobBuilderFactory.get("JDBC Batch to JDBC Batch Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToJdbcBatchStep())
            .build();
    }

    @Bean
    public Job jdbcBatchToItemWriterAdapterJob() {
        return jobBuilderFactory.get("JDBC Batch to Rest Item Writer Adapter Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcBatchToItemWriterAdapterStep())
            .build();
    }



    /*
    Following are Steps
     */

    public Step jdbcBatchToFlatFileItemStep() {
        return stepBuilderFactory.get("JDBC Batch to CSV File Item Step")
            .<StudentJdbc, StudentJdbc>chunk(100)
            .reader(studentJdbcCursorItemReader)
            .writer(studentJdbcFlatFileItemWriter)
            .build();
    }

    public Step jdbcBatchToJsonFileItemStep() {
        return stepBuilderFactory.get("JDBC Batch to JSON File Item Step")
            .<StudentJdbc, StudentJdbc>chunk(100)
            .reader(studentJdbcCursorItemReader)
            .writer(studentJdbcJsonFileItemWriter)
            .build();
    }

    public Step jdbcBatchToJsonFileItemWithProcessorStep() {
        return stepBuilderFactory.get("JDBC Batch to JSON File Item Step")
            .<StudentJdbc, StudentJson>chunk(100)
            .reader(studentJdbcCursorItemReader)
            .processor(studentJdbcToJsonProcessor)
            .writer(studentJdbcToJsonFileItemWriter)
            .build();
    }

    public Step jdbcBatchToXmlFileItemWithProcessorStep() {
        return stepBuilderFactory.get("JDBC Batch to XML File Item Step")
            .<StudentJdbc, StudentXml>chunk(100)
            .reader(studentJdbcCursorItemReader)
            .processor(studentJdbcToXmlProcessor)
            .writer(studentXmlStaxEventItemWriter)
            .build();
    }

    public Step jdbcBatchToJdbcBatchStep() {
        return stepBuilderFactory.get("JDBC Batch to JDBC Batch Item Step")
            .<StudentJdbc, StudentJdbc>chunk(100)
            .reader(studentJdbcCursorItemReader)
//            .writer(studentJdbcJBatchItemWriter)  //using regular statement
            .writer(studentJdbcJBatchItemWriterUsingPreparedStatement)  //using prepared statement
            .build();
    }

    public Step jdbcBatchToItemWriterAdapterStep() {
        return stepBuilderFactory.get("JDBC Batch to JDBC Batch Item Step")
            .<StudentJdbc, StudentJdbc>chunk(500)
            .reader(studentJdbcCursorItemReader)
            .writer(studentJdbcItemWriterAdapter)
            .build();
    }
}
