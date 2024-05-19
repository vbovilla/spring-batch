package in.dminc.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.dminc.model.Student;
import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import in.dminc.service.StudentService;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;

@Component
public class FileItemReadersConfiguration {

    @Bean
    public FlatFileItemReader<Student> studentFlatFileItemReader() {
        FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-04\\src\\main\\resources\\data\\students_1000.csv")));

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "GENDER", "PHONE_NUMBER");
        lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public JsonItemReader<StudentJson> studentJsonItemReader() {
        JsonItemReader<StudentJson> reader = new JsonItemReader<>();
        reader.setResource(new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-04\\src\\main\\resources\\data\\students_10000.json")));
        reader.setJsonObjectReader(new JacksonJsonObjectReader<>(StudentJson.class));
        return reader;
    }

    // stax --> streaming api for xml
    @Bean
    public StaxEventItemReader<StudentXml> studentXmlStaxEventItemReader() {
        StaxEventItemReader<StudentXml> reader = new StaxEventItemReader<>();
        reader.setResource(new FileSystemResource(new File("C:\\working\\spring\\spring-batch\\spring-batch-04\\src\\main\\resources\\data\\students_1000.xml")));
        reader.setEncoding("UTF-8");
        reader.setFragmentRootElementName("student");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(StudentXml.class);
        reader.setUnmarshaller(marshaller);

        return reader;
    }

    @Autowired
    DataSource datasource;

    @Autowired
    DataSource universityDataSource;

    @Bean
    public JdbcCursorItemReader<StudentJdbc> studentJdbcJdbcCursorItemReader() {
        JdbcCursorItemReader<StudentJdbc> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(universityDataSource);
        reader.setSql("select id, first_name, last_name, email, gender, phone_number from students");

        BeanPropertyRowMapper<StudentJdbc> beanPropertyRowMapper = new BeanPropertyRowMapper<>();
        beanPropertyRowMapper.setMappedClass(StudentJdbc.class);
        reader.setRowMapper(beanPropertyRowMapper);

        return reader;
    }


    @Autowired
    StudentService studentService;

    @Bean
    public ItemReaderAdapter<Student> studentItemReaderAdapter() {
        ItemReaderAdapter<Student> reader = new ItemReaderAdapter<>();
        reader.setTargetObject(studentService);
        reader.setTargetMethod("retrieveStudents");
        return reader;
    }
}
