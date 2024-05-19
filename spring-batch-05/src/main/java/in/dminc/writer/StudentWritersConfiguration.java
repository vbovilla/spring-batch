package in.dminc.writer;

import in.dminc.model.StudentJdbc;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

@Configuration
public class StudentWritersConfiguration {

    @Bean
    public FlatFileItemWriter<StudentJdbc> studentJdbcFlatFileItemWriter() {
        FlatFileItemWriter<StudentJdbc> writer = new FlatFileItemWriter<>();
        FileSystemResource resource =
            new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-05\\src\\main\\resources\\output-data\\students.csv"));
        writer.setResource(resource);

        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("ID, FIRST_NAME, LAST_NAME, EMAIL, GENDER, PHONE_NUMBER");
            }
        });

        DelimitedLineAggregator<StudentJdbc> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<StudentJdbc> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"id", "firstName", "lastName", "email", "gender", "phoneNumber"});
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

        writer.setLineAggregator(delimitedLineAggregator);

        return writer;
    }
}
