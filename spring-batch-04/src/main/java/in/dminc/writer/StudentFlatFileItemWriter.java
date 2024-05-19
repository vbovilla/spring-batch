package in.dminc.writer;

import in.dminc.model.Student;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFlatFileItemWriter implements ItemWriter<Student> {
    @Override
    public void write(List<? extends Student> items) throws Exception {
        System.out.println("inside flat file item writer.. processing " + items.size() + " records");
    }
}
