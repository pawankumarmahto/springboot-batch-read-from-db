package com.pawan.springboo.batch.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(value = "spring.batch.job.enabled", matchIfMissing = false)
public class SchedulerJobConfig {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;
	

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	//@Scheduled(fixedDelay = 5000, initialDelay = 5000)
	//@Scheduled(cron = "0 0 0/12 * * ?")
	@Scheduled(cron = "${spring.batch.cron}")
	public void scheduleByFixedRate() throws Exception {
		
		System.out.println("Batch job starting");
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
		jobLauncher.run(job, jobParameters);

		System.out.println("Batch job executed successfully\n");
	}
}
