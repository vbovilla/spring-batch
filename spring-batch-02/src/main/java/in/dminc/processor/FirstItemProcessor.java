package in.dminc.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer, Long> {
    @Override
    public Long process(Integer integer) throws Exception {
        System.out.println("inside item processor");
        integer = integer + 100;
        return Long.valueOf(integer);
    }
}
