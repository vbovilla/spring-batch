package in.dminc.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecondItemWriter implements ItemWriter<Integer> {
    @Override
    public void write(List<? extends Integer> items) throws Exception {
        // size of 'items' is same as chunk size.
        System.out.println("inside second item writer");
        items.forEach(System.out::println);
        Thread.sleep(10000);
    }
}
