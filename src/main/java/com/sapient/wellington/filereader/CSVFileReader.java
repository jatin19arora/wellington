package com.sapient.wellington.filereader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class CSVFileReader implements FileReader {

	@Override
	public void readFile() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Path myDir = Paths.get("C:/wellington/data");
		WatchService watcher = null;
		try {
			watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WatchQueueReader fileWatcher = new WatchQueueReader(watcher);
		executor.execute(fileWatcher);

	}

	private class WatchQueueReader implements Runnable {
		/** the watchService that is passed in from above */
		private WatchService watcher;

		public WatchQueueReader(WatchService myWatcher) {
			this.watcher = myWatcher;
		}

		@Override
		public void run() {
			try {
				WatchKey watchKey = watcher.take();
				while (watchKey != null) {
					List<WatchEvent<?>> events = watchKey.pollEvents();
					for (WatchEvent event : events) {
						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
							System.out.println("Created: " + event.context().toString());
						}
					}
					watchKey.reset();
					watchKey = watcher.take();
				}

			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}

		}

	}

}
