package in.dminc.processor;

import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import in.dminc.model.StudentXml;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentJdbcToXmlProcessor implements ItemProcessor<StudentJdbc, StudentXml> {

    @Override
    public StudentXml process(StudentJdbc item) throws Exception {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(item, StudentXml.class);
    }
}
