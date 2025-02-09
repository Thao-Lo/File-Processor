package com.zavis.fileprocessor.file_processor.Factory;

import com.zavis.fileprocessor.file_processor.Processor.CSVProcessor;
import com.zavis.fileprocessor.file_processor.Processor.FileProcessor;
import com.zavis.fileprocessor.file_processor.Processor.JSONProcessor;
import com.zavis.fileprocessor.file_processor.Processor.XMLProcessor;

public class FileProcessorFactory {

	// factory pattern
	public static FileProcessor getProcessor(String filePath) { // order1.csv, order2.json, order3.xml
		if (filePath.endsWith(".csv")) {
			return new CSVProcessor();
		} else if (filePath.endsWith(".json")) {
			return new JSONProcessor();
		} else if (filePath.endsWith(".xml")) {
			return new XMLProcessor();
		} else {
			throw new IllegalArgumentException("Unsupported file type: " + filePath);
		}
	}
}
