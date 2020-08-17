package springbatchexample.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@RestController
//@RequestMapping("/load")
@Component
public class Loadcontroller {
	

	    private final Job job;
	    private final JobLauncher jobLauncher;

	    @Autowired
	    public Loadcontroller(Job job, JobLauncher jobLauncher) {
	        this.job = job;
	        this.jobLauncher = jobLauncher;
	    }

	    @Scheduled(cron = "0/10 * * * * *")
	    public void runSpringBatchExampleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

	        jobLauncher.run(job, newExecution());

	    }

	    private JobParameters newExecution() {
	        Map<String, JobParameter> parameters = new HashMap <String, JobParameter> ();

	        JobParameter parameter = new JobParameter(new Date());
	        parameters.put("currentTime", parameter);

	        return new JobParameters(parameters);
	    }

	/*
	 * @Autowired JobLauncher joblauncher;
	 * 
	 * @Autowired Job job;
	 * 
	 * @GetMapping public BatchStatus load() throws
	 * JobExecutionAlreadyRunningException, JobRestartException,
	 * JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	 * Map<String, JobParameter> map=new HashMap<String, JobParameter>();
	 * map.put("time", new JobParameter(System.currentTimeMillis())); JobParameters
	 * jobParameters=new JobParameters(map);
	 * org.springframework.batch.core.JobExecution jobExecution=joblauncher.run(job,
	 * jobParameters);
	 * 
	 * System.out.println("Job execution"+jobExecution.getStatus());
	 * System.out.println("Batch is running"); while(jobExecution.isRunning()) {
	 * System.out.println("....."); } return jobExecution.getStatus(); }
	 */
}
