package in.dminc.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("FirstStepListener.beforeStep");
        printStepInformation(stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("FirstStepListener.afterStep");
        printStepInformation(stepExecution);
        return ExitStatus.COMPLETED;
    }

    private void printStepInformation(StepExecution stepExecution) {
        System.out.println("StepName: " + stepExecution.getStepName());
        System.out.println("StepJobParameters: " + stepExecution.getJobParameters());
        System.out.println("StepJobParameters.parameters: " + stepExecution.getJobParameters().getParameters());
        System.out.println("StepExecutionContext: " + stepExecution.getExecutionContext());
        System.out.println("JobExecutionContext: " + stepExecution.getJobExecution().getExecutionContext());
    }
}
