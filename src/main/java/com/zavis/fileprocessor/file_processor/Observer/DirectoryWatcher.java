package com.zavis.fileprocessor.file_processor.Observer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zavis.fileprocessor.file_processor.Service.FileProcessorService;

@Service
public class DirectoryWatcher {
	private static final Logger logger = LoggerFactory.getLogger(DirectoryWatcher.class);

	private final Path directory;
	private final FileProcessorService fileProcessorService;

	public DirectoryWatcher(@Value("${file.upload-dir}") String dir, FileProcessorService service) {
		this.directory = Paths.get(dir); // C:/upload
		this.fileProcessorService = service;
	}

	public void startWatching() throws IOException, InterruptedException {
		// an observer monitoring file changes in a registered directory
		WatchService watchService = FileSystems.getDefault().newWatchService();
		directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		logger.info("Watching directory: {}", directory);
		while (true) {			
			WatchKey key = watchService.take(); //waiting for new file created	
			
			for (WatchEvent<?> event : key.pollEvents()) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) { //what kind of change happened
					Path newFile = directory.resolve((Path) event.context()); // c:/upload/order1.csv
					fileProcessorService.processFile(newFile.toString()); //factory pattern
				}
				
			}
			key.reset(); // reactivate watchkey to continue watching 
		}
	}
}
