package in.dminc.processor;

import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentJson;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentJdbcToJsonProcessor implements ItemProcessor<StudentJdbc, StudentJson> {

    @Override
    public StudentJson process(StudentJdbc item) throws Exception {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(item, StudentJson.class);
    }
}
