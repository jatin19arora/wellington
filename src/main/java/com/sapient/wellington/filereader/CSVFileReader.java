package com.sapient.wellington.filereader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CSVFileReader implements FileReader {

	@Override
	public void readFile() {
		Path myDir = Paths.get("D:/data");

		try {
			WatchService watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

			WatchKey watckKey = watcher.take();

			List<WatchEvent<?>> events = watckKey.pollEvents();
			for (WatchEvent event : events) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println("Created: " + event.context().toString());
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

}
