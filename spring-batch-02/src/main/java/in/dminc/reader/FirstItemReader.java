package in.dminc.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    int index = 0;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("inside first item reader");
        // return elements from 'list' one by one.
        Integer item;
        if (index < list.size()) {
            item = list.get(index);
            index++;
            return item;
        }
        // reset index=0, if we want to rerun the application.
        index = 0;
        // if there are no records to read from 'list', then return null.
        return null;
    }
}
