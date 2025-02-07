package com.zavis.fileprocessor.file_processor.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfig {
	@Bean
	public ExecutorService executorService() {		
		return Executors.newFixedThreadPool(4); //to process 4 files asynchonous, more than 4 will be move to queue
	}	
}

//@Configuration
//@EnableAsync
//public class ExecutorConfig {
//
//	@Bean(name = "asyncExecutor")
//	public TaskExecutor asyncExecutor() {		
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(4);
//		executor.setMaxPoolSize(5);
//		executor.setQueueCapacity(15);
//		executor.setThreadNamePrefix("AsyncExecutor-");
//		executor.initialize();
//		return executor;
//	}
//}
