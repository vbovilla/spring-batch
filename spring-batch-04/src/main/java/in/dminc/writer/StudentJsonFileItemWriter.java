package in.dminc.writer;

import in.dminc.model.StudentJson;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentJsonFileItemWriter implements ItemWriter<StudentJson> {
    @Override
    public void write(List<? extends StudentJson> items) throws Exception {
        System.out.println("inside json file item writer.. processing " + items.size() + " records");
    }
}
