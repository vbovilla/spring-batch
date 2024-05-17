package in.dminc.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecondJobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job secondJob;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStarter() {
        System.out.println("Starting Second Job");

        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

        try {
            JobExecution execution = jobLauncher.run(secondJob, new JobParameters(params));
            System.out.println("Job Execution Id : " + execution.getId());
        } catch (JobExecutionAlreadyRunningException e) {
            System.out.println("JobExecutionAlreadyRunningException : " + e.getMessage());
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            System.out.println("JobRestartException : " + e.getMessage());
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            System.out.println("JobInstanceAlreadyCompleteException : " + e.getMessage());
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            System.out.println("JobParametersInvalidException : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
