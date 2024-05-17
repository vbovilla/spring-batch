package in.dminc.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("FirstJobListener::beforeJob");
        printJobInformation(jobExecution);
        jobExecution.getExecutionContext().put("name", "Vishnu V. Bovilla");
        jobExecution.getExecutionContext().put("email", "vishnu.bovilla@gmail.com");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("FirstJobListener::afterJob");
        printJobInformation(jobExecution);
    }

    private void printJobInformation(JobExecution jobExecution) {
        System.out.println("JobName: " + jobExecution.getJobInstance().getJobName());
        System.out.println("JobParameters: " + jobExecution.getJobParameters());
        System.out.println("JobParameters.parameters: " + jobExecution.getJobParameters().getParameters());
        System.out.println("ExecutionContext: " + jobExecution.getExecutionContext());
    }
}
