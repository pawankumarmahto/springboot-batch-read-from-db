package com.pawan.springboo.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pawan.springboo.batch.entity.DebitCard;
import com.pawan.springboo.batch.steps.DebitCardProcessor;
import com.pawan.springboo.batch.steps.DebitCardWriter;
import com.pawan.springboo.batch.steps.JpaCardReader;

@Configuration
@EnableAsync
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DebitCardProcessor debitCardProcessor;

	@Autowired
	JpaCardReader jpaCardReader;

	@Autowired
	DebitCardWriter debitCardWriter;

	
	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("MyJob").incrementer(new RunIdIncrementer()).flow(createStep()).end().build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("MyStep").<DebitCard, DebitCard>chunk(3)
				// .reader(debitCardReader)
				.reader(jpaCardReader).processor(debitCardProcessor).writer(debitCardWriter).build();
	}
}
