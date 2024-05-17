package in.dminc.config;

import in.dminc.model.Student;
import in.dminc.processor.FirstItemProcessor;
import in.dminc.reader.FirstItemReader;
import in.dminc.writer.FirstItemWriter;
import in.dminc.writer.StudentCSVFlatFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class SimpleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    FirstItemReader firstItemReader;
    @Autowired
    FirstItemProcessor firstItemProcessor;
    @Autowired
    FirstItemWriter firstItemWriter;

    @Autowired
    StudentCSVFlatFileItemWriter studentCSVFlatFileItemWriter;

    @Bean
    public Job buildSimpleJob() {
        return jobBuilderFactory.get("Chunk Job")
            .incrementer(new RunIdIncrementer())
            .start(secondChunkStep())
            .build();
    }

    private Step firstChunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
            .<Integer, Long>chunk(4)
            .reader(firstItemReader)
            .processor(firstItemProcessor)
            .writer(firstItemWriter)
            .build();
    }

    private Step secondChunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
            .<Student, Student>chunk(99)
            .reader(studentFlatFileItemReader())
            .writer(studentCSVFlatFileItemWriter)
            .build();
    }

    private FlatFileItemReader<Student> studentCSVFlatFileItemReader() {
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setResource(new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-03\\src\\main\\resources\\data\\students_backup.csv")));
        flatFileItemReader.setLinesToSkip(1);

        flatFileItemReader.setLineMapper(new DefaultLineMapper<Student>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "GENDER", "PHONE_NUMBER");
//                        setDelimiter(DELIMITER_COMMA);    // , is default delimeter
                        setDelimiter("|");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
                    {
                        setTargetType(Student.class);
                    }
                });
            }
        });

        return flatFileItemReader;
    }


    private FlatFileItemReader<Student> studentFlatFileItemReader() {
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setResource(new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-03\\src\\main\\resources\\data\\students_backup.csv")));
        flatFileItemReader.setLinesToSkip(1);

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter("|");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "GENDER", "PHONE_NUMBER");

        BeanWrapperFieldSetMapper<Student> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Student.class);

        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        flatFileItemReader.setLineMapper(defaultLineMapper);


        return flatFileItemReader;
    }
}
