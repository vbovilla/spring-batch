package in.dminc.writer;

import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import in.dminc.service.StudentService;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.support.ColumnMapItemPreparedStatementSetter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class StudentWritersConfiguration {

    @Qualifier("universityDataSource")
    @Autowired
    DataSource universityDataSource;

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
    StaxEventItemWriter<StudentXml> studentXmlStaxEventItemWriter() {
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

    @Bean
    public JdbcBatchItemWriter<StudentJdbc> studentJdbcJBatchItemWriter() {
        JdbcBatchItemWriter<StudentJdbc> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(universityDataSource);
        writer.setSql("insert into student(id, first_name, last_name, email, gender, phone_number) values(:id, :firstName, :lastName, :email, :gender, :phoneNumber)");

        BeanPropertyItemSqlParameterSourceProvider<StudentJdbc> sourceProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        writer.setItemSqlParameterSourceProvider(sourceProvider);

        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<StudentJdbc> studentJdbcJBatchItemWriterUsingPreparedStatement() {
        JdbcBatchItemWriter<StudentJdbc> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(universityDataSource);
        writer.setSql("insert into student(id, first_name, last_name, email, gender, phone_number) values(?,?,?,?,?,?)");

        writer.setItemPreparedStatementSetter(new ItemPreparedStatementSetter<StudentJdbc>() {
            @Override
            public void setValues(StudentJdbc item, PreparedStatement ps) throws SQLException {
                ps.setLong(1, item.getId());
                ps.setString(2, item.getFirstName());
                ps.setString(3, item.getLastName());
                ps.setString(4, item.getEmail());
                ps.setString(5, item.getGender());
                ps.setString(6, item.getPhoneNumber());
            }
        });

        return writer;
    }

    @Autowired
    StudentService studentService;

    @Bean
    public ItemWriterAdapter<StudentJdbc> studentJdbcItemWriterAdapter() {
        ItemWriterAdapter<StudentJdbc> writer = new ItemWriterAdapter<>();
        writer.setTargetObject(studentService);
        writer.setTargetMethod("writeStudents");

        return writer;
    }
}
