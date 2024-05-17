package in.dminc.config;

import in.dminc.listener.FirstJobListener;
import in.dminc.listener.FirstStepListener;
import in.dminc.tasklet.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
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
    SecondTasklet secondTasklet;
    @Autowired
    FirstJobListener firstJobListener;
    @Autowired
    FirstStepListener firstStepListener;

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("First Job")
            .incrementer(new RunIdIncrementer())
            .start(firstStep())
            .next(secondStep())
            .listener(firstJobListener)
            .build();
    }

    private Step firstStep() {
        return stepBuilderFactory.get("First Step")
            .tasklet(firstTasklet())
            .listener(firstStepListener)
            .build();
    }

    private Step secondStep() {
//        return stepBuilderFactory.get("Second Step")
//            .tasklet(secondTasklet())
//            .build();

        return stepBuilderFactory.get("Second Step")
            .tasklet(secondTasklet)
            .build();
    }

    private Tasklet firstTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("first tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }

    private Tasklet secondTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Second tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
