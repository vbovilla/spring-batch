package in.dminc.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

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

    @Bean
    public JsonFileItemWriter<StudentJdbc> studentJdbcJsonFileItemWriter() {
        FileSystemResource resource =
            new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-05\\src\\main\\resources\\output-data\\students.json"));
        JacksonJsonObjectMarshaller<StudentJdbc> jsonObjectMarshaller = new JacksonJsonObjectMarshaller<>();

        return new JsonFileItemWriter<>(resource, jsonObjectMarshaller);
    }

    @Bean
    public JsonFileItemWriter<StudentJson> studentJdbcToJsonFileItemWriter() {
        FileSystemResource resource =
            new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-05\\src\\main\\resources\\output-data\\students.json"));
        JacksonJsonObjectMarshaller<StudentJson> jsonObjectMarshaller = new JacksonJsonObjectMarshaller<>();

        return new JsonFileItemWriter<>(resource, jsonObjectMarshaller);
    }

    @Bean
    StaxEventItemWriter<StudentXml> studentXmlStaxEventItemWriter(){
        StaxEventItemWriter<StudentXml> writer = new StaxEventItemWriter<>();
        FileSystemResource resource =
            new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-05\\src\\main\\resources\\output-data\\students.xml"));
        writer.setResource(resource);
        writer.setRootTagName("students");
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(StudentXml.class);
        writer.setMarshaller(marshaller);

        return writer;
    }
}
