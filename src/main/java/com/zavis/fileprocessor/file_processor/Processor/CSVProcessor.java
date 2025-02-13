package com.zavis.fileprocessor.file_processor.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zavis.fileprocessor.file_processor.Entity.Order;
import com.zavis.fileprocessor.file_processor.Repository.OrderRepository;

@Component
public class CSVProcessor implements FileProcessor {
	private static final Logger logger = LoggerFactory.getLogger(CSVProcessor.class);
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void process(String filePath) {
		List<Order> orders = new ArrayList<>();

		// try-with-resource auto close() file after finished reading
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
			String line;
			boolean isFirstLine = true;
			
			//read each line
			while ((line = reader.readLine()) != null) {
				// skip header line
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				String[] values = line.split(",");
				if(values.length < 5) {
					logger.warn("Missing data: {}", line);
					continue;
				}
				Order order = new Order();
				order.setOrderId(Long.parseLong(values[0]));
				order.setCustomerName(values[1]);
				order.setProductName(values[2]);
				order.setQuantity(Integer.parseInt(values[3]));
				order.setPrice(Double.parseDouble(values[4]));
				
				orders.add(order);
			}
			//save to db
			orderRepository.saveAll(orders);
			logger.info("Successfully processed CSV file: {}", filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
