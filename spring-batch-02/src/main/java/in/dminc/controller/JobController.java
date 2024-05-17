package in.dminc.controller;

import in.dminc.params.RequestParams;
import in.dminc.service.JobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {

//    @Autowired
//    JobLauncher jobLauncher;

//    @Autowired
//    Job secondJob;

    @Autowired
    JobService jobService;

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        Map<String, JobParameter> params = new HashMap<>();
//        params.put("currentTime", new JobParameter(System.currentTimeMillis()));

//        if (jobName.equalsIgnoreCase("First Job")) {
//            return "No Job found with '" + jobName + "'";
//        } else if (jobName.equalsIgnoreCase("Second Job")) {
//            jobLauncher.run(secondJob, new JobParameters(params));
//        } else {
//            return "No Job found with '" + jobName + "'";
//        }
        jobService.startJob(jobName);
        return "Job started...!!";
    }

    @PostMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<RequestParams> jobParams) {
        jobService.startJob(jobName, jobParams);
        return "Job started...!!";
    }

    @Autowired
    JobOperator jobOperator;

    @GetMapping("/stop/{jobExecutionId}")
    public String stopJob(@PathVariable long jobExecutionId) {
        System.out.println("Stopping job with execution id : " + jobExecutionId);
        try {
            jobOperator.stop(jobExecutionId);
        } catch (NoSuchJobExecutionException e) {
            System.out.println("NoSuchJobExecutionException : " + e.getMessage());
            throw new RuntimeException(e);
        } catch (JobExecutionNotRunningException e) {
            System.out.println("JobExecutionNotRunningException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return "Job stopped...!!";
    }
}
