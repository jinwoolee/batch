package kr.or.ddit.yogult.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class YogultMain {
	public static void main(String[] args){
		ConfigurableApplicationContext context =
			new ClassPathXmlApplicationContext("classpath:kr/or/ddit/config/spring/context-batch.xml");
		
		//jobLauncher, job 받아와서(DL)
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job yogultJob = context.getBean("yogultJob", Job.class);
		
		JobParametersBuilder jobBuilder= new JobParametersBuilder();
        jobBuilder.addString("ym", "201903");
        jobBuilder.addLong("dt", System.currentTimeMillis());
		
		
		try {
			jobLauncher.run(yogultJob, jobBuilder.toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		
		context.close();
	}
}










