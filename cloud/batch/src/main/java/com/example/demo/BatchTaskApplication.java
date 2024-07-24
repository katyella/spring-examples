package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class BatchTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchTaskApplication.class, args);
	}

	@Bean
	public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		Step step = new StepBuilder("step", jobRepository)
				.<String, String>chunk(1, transactionManager)
				.reader(() -> "Hello, Spring Batch!")
				.writer(System.out::println)
				.build();

		return new JobBuilder("job", jobRepository)
				.start(step)
				.build();
	}

	@Bean
	public CommandLineRunner commandLineRunner(JobLauncher jobLauncher, Job job, Environment environment) {
		return args -> {
			jobLauncher.run(job, new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters());
		};
	}

}