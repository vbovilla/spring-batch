package in.dminc.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {
    @Override
    public void write(List<? extends Long> items) throws Exception {
        // size of 'items' is same as chunk size.
        System.out.println("inside item writer");
        items.forEach(System.out::println);
    }
}
