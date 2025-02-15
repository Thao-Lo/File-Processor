package com.zavis.fileprocessor.file_processor.Processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavis.fileprocessor.file_processor.Entity.Order;
import com.zavis.fileprocessor.file_processor.Repository.OrderRepository;

@Component
public class JSONProcessor implements FileProcessor {
	private static final Logger logger = LoggerFactory.getLogger(JSONProcessor.class);
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void process(String filePath) {
		try {
			List<Order> orders = new ArrayList<>();
			
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(filePath);
			if(file.length() == 0) {
				logger.warn("File is empty: {}", filePath);
				return;
			}
			
			orders = objectMapper.readValue(file, new TypeReference<List<Order>>(){});
			if(orders.isEmpty()) {
				logger.warn("No data in file.");
			}
			//save to db
			orderRepository.saveAll(orders);
			logger.info("Successfully processed JSON file: {}", filePath);
		} catch (IOException e) {
			logger.error("Error reading JSON file: {}", e.getMessage());
		}
	}

}
