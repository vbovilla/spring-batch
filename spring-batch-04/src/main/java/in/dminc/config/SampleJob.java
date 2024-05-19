package in.dminc.config;

import in.dminc.model.Student;
import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import in.dminc.writer.StudentFlatFileItemWriter;
import in.dminc.writer.StudentItemWriterAdapter;
import in.dminc.writer.StudentJdbcBatchItemWriter;
import in.dminc.writer.StudentJsonFileItemWriter;
import in.dminc.writer.StudentXmlFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
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
    FlatFileItemReader<Student> studentFlatFileItemReader;
    @Autowired
    JsonItemReader<StudentJson> studentJsonItemReader;
    @Autowired
    StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader;
    @Autowired
    JdbcCursorItemReader<StudentJdbc> studentJdbcJdbcCursorItemReader;
    @Autowired
    ItemReaderAdapter studentItemReaderAdapter;

    @Autowired
    StudentFlatFileItemWriter studentFlatFileItemWriter;
    @Autowired
    StudentJsonFileItemWriter studentJsonFileItemWriter;
    @Autowired
    StudentXmlFileItemWriter studentXmlFileItemWriter;
    @Autowired
    StudentJdbcBatchItemWriter studentJdbcBatchItemWriter;
    @Autowired
    StudentItemWriterAdapter studentItemWriterAdapter;


    public Job flatFileJob() {
        return jobBuilderFactory.get("Sample Job")
            .incrementer(new RunIdIncrementer())
            .start(csvFlatFileStep()).build();
    }

    public Job jsonFileJob() {
        return jobBuilderFactory.get("JSON File Job")
            .incrementer(new RunIdIncrementer())
            .start(jsonFileStep()).build();
    }

    public Job xmlFileJob() {
        return jobBuilderFactory.get("XML File Job")
            .incrementer(new RunIdIncrementer())
            .start(xmlFileStep()).build();
    }

    public Job jdbcBatchItemJob() {
        return jobBuilderFactory.get("JDBC Batch Item Job")
            .incrementer(new RunIdIncrementer())
            .start(jdbcbatchItemStep()).build();
    }

    @Bean
    public Job restApiItemJob() {
        return jobBuilderFactory.get("Rest Api Item Job")
            .incrementer(new RunIdIncrementer())
            .start(restApiItemStep()).build();
    }


    public Step csvFlatFileStep() {
        return stepBuilderFactory.get("CSV Flat File Step")
            .<Student, Student>chunk(100)
            .reader(studentFlatFileItemReader)
            .writer(studentFlatFileItemWriter)
            .build();
    }

    public Step jsonFileStep() {
        return stepBuilderFactory.get("JSON File Step")
            .<StudentJson, StudentJson>chunk(100)
            .reader(studentJsonItemReader)
            .writer(studentJsonFileItemWriter)
            .build();
    }

    public Step xmlFileStep() {
        return stepBuilderFactory.get("XML File Step")
            .<StudentXml, StudentXml>chunk(100)
            .reader(studentXmlStaxEventItemReader)
            .writer(studentXmlFileItemWriter)
            .build();
    }

    public Step jdbcbatchItemStep() {
        return stepBuilderFactory.get("JDBC Batch Item Step")
            .<StudentJdbc, StudentJdbc>chunk(100)
            .reader(studentJdbcJdbcCursorItemReader)
            .writer(studentJdbcBatchItemWriter)
            .build();
    }

    public Step restApiItemStep() {
        return stepBuilderFactory.get("Rest API Item Step")
            .<Student, Student>chunk(100)
            .reader(studentItemReaderAdapter)
            .writer(studentItemWriterAdapter)
            .build();
    }


}
