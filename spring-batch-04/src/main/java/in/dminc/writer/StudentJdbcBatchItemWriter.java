package in.dminc.writer;

import in.dminc.model.Student;
import in.dminc.model.StudentJdbc;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentJdbcBatchItemWriter implements ItemWriter<StudentJdbc> {
    @Override
    public void write(List<? extends StudentJdbc> items) throws Exception {
        System.out.println("inside jdbc batch item writer.. processing " + items.size() + " records");
    }
}
