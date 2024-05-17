package in.dminc.configuration;

import in.dminc.processor.FirstItemProcessor;
import in.dminc.reader.FirstItemReader;
import in.dminc.reader.SecondItemReader;
import in.dminc.writer.FirstItemWriter;
import in.dminc.writer.SecondItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    FirstItemReader firstItemReader;
    @Autowired
    FirstItemProcessor firstItemProcessor;
    @Autowired
    FirstItemWriter firstItemWriter;
    @Autowired
    private SecondItemWriter secondItemWriter;
    @Autowired
    private SecondItemReader secondItemReader;


    @Bean
    public Job secondJob() {
        return jobBuilderFactory.get("Second Job")
            .incrementer(new RunIdIncrementer())
            .start(firstChunkStep())
            .next(secondChunkStep())
            .next(thirdTaskletStep())
            .build();
    }

    private Step firstChunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
            .<Integer, Long>chunk(1)
            .reader(firstItemReader)
            .processor(firstItemProcessor)
            .writer(firstItemWriter)
            .build();
    }

    private Step secondChunkStep() {
        // 'processor' is optional
        // 'reader' and 'writer' are mandatory. that is, ItemReader & ItemWriter must be provided
        return stepBuilderFactory.get("Second Chunk Step")
            .<Integer, Integer>chunk(1)
            .reader(secondItemReader)
            .writer(secondItemWriter)
            .build();
    }

    private Step thirdTaskletStep() {
        return stepBuilderFactory.get("Third Tasklet Step")
            .tasklet(sampleTasklet())
            .build();
    }

//    private Tasklet sampleTasklet() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("inside sample tasklet");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

    private Tasklet sampleTasklet() {
        return (stepContribution, chunkContext) -> {
            System.out.println("inside sample tasklet");
            return RepeatStatus.FINISHED;
        };
    }

}
