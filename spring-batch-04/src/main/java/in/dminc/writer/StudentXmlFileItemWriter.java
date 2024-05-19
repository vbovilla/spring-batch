package in.dminc.writer;

import in.dminc.model.StudentXml;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentXmlFileItemWriter implements ItemWriter<StudentXml> {
    @Override
    public void write(List<? extends StudentXml> items) throws Exception {
        System.out.println("inside xml file item writer.. processing " + items.size() + " records");
    }
}
