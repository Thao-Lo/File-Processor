package com.zavis.fileprocessor.file_processor.Service;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zavis.fileprocessor.file_processor.Factory.FileProcessorFactory;

@Service
public class FileProcessorService {
	@Autowired
	private ExecutorService executor;
	@Autowired 
	FileProcessorFactory fileProcessorFactory;
	
	public void processFile(String filePath) {
		//pick a free thread to process a file, if not will be place in queue
		executor.submit(() -> {
			fileProcessorFactory.getProcessor(filePath).process(filePath);			
		});
	}
}
