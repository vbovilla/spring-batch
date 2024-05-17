package in.dminc.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {
    @Override
    public void write(List<? extends Long> items) throws Exception {
        System.out.println("inside first item writer.. processing " + items.size() + " items");
        items.forEach(System.out::println);
    }
}
