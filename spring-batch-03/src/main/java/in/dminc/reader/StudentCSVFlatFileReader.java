package in.dminc.reader;

import in.dminc.model.Student;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class StudentCSVFlatFileReader {

    @Bean
    public FlatFileItemReader<Student> reader() {
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
