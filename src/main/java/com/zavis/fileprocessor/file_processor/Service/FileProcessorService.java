package com.zavis.fileprocessor.file_processor.Service;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;

import com.zavis.fileprocessor.file_processor.Factory.FileProcessorFactory;
import com.zavis.fileprocessor.file_processor.Processor.FileProcessor;

public class FileProcessorService {
	@Autowired
	private ExecutorService executor;
	
	public void processFile(String filePath) {
		//pick a free thread to process a file, if not will be place in queue
		executor.submit(() -> {
			FileProcessor processor = FileProcessorFactory.getProcessor(filePath);
			processor.process(filePath);
		});
	}
}
