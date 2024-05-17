package in.dminc.service;

import in.dminc.params.RequestParams;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job secondJob;

    @Async
    public void startJob(String jobName) {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

        if (jobName.equalsIgnoreCase("First Job")) {
            System.out.println("No Job found with '" + jobName + "'");
        } else if (jobName.equalsIgnoreCase("Second Job")) {
            try {
                System.out.println("Started " + jobName);
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
        } else {
            System.out.println("No Job found with '" + jobName + "'");
        }
    }

    @Async
    public void startJob(String jobName, List<RequestParams> jobParams) {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));
        jobParams.forEach(param -> params.put(param.getParamName(), new JobParameter(param.getParamValue())));

        if (jobName.equalsIgnoreCase("First Job")) {
            System.out.println("No Job found with '" + jobName + "'");
        } else if (jobName.equalsIgnoreCase("Second Job")) {
            try {
                System.out.println("Started " + jobName);
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
        } else {
            System.out.println("No Job found with '" + jobName + "'");
        }
    }
}
