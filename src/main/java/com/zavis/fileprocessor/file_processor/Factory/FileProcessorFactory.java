package com.zavis.fileprocessor.file_processor.Factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zavis.fileprocessor.file_processor.Processor.CSVProcessor;
import com.zavis.fileprocessor.file_processor.Processor.FileProcessor;
import com.zavis.fileprocessor.file_processor.Processor.JSONProcessor;
import com.zavis.fileprocessor.file_processor.Processor.XMLProcessor;

@Component
public class FileProcessorFactory {

	private final Map<String, FileProcessor> processorMap;
		
	@Autowired //constructor injection List<FileProcessor> processors;
	public FileProcessorFactory(List<FileProcessor> processors) {
		this.processorMap = new HashMap<>();
		for(FileProcessor processor : processors) {
			if(processor instanceof CSVProcessor) {
				processorMap.put("csv", processor);
			}else if(processor instanceof JSONProcessor) {
				processorMap.put("json", processor);
			}else if(processor instanceof XMLProcessor) {
				processorMap.put("xml", processor);
			}
		}
	}
	public FileProcessor getProcessor(String filePath) {
		//order.csv -> +1 to get "csv" not ".csv"
		String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
		FileProcessor processor = processorMap.get(fileExtension);
		
		if(processor == null) {
			throw new IllegalArgumentException("Unsupported file type: " + filePath);
		}
		return processor;
		
	}
}

// factory pattern
//public static FileProcessor getProcessor(String filePath) { // order1.csv, order2.json, order3.xml
//	if (filePath.endsWith(".csv")) {
//		return new CSVProcessor();
//	} else if (filePath.endsWith(".json")) {
//		return new JSONProcessor();
//	} else if (filePath.endsWith(".xml")) {
//		return new XMLProcessor();
//	} else {
//		throw new IllegalArgumentException("Unsupported file type: " + filePath);
//	}
//}