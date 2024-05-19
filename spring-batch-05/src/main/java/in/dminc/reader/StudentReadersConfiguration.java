package in.dminc.reader;

import in.dminc.model.StudentJdbc;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class StudentReadersConfiguration {

    @Qualifier("universityDataSource")
    @Autowired
    DataSource universityDataSource;

    @Bean
    public JdbcCursorItemReader<StudentJdbc> studentJdbcCursorItemReader() {
        JdbcCursorItemReader<StudentJdbc> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(universityDataSource);
        reader.setSql("select id, first_name, last_name, email, gender, phone_number from students");

        BeanPropertyRowMapper<StudentJdbc> beanPropertyRowMapper = new BeanPropertyRowMapper<>();
        beanPropertyRowMapper.setMappedClass(StudentJdbc.class);
        reader.setRowMapper(beanPropertyRowMapper);

        return reader;
    }
}
