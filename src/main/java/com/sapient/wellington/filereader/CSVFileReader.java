package com.sapient.wellington.filereader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.wellington.pojo.Order;
import com.sapient.wellington.pojo.TransactionType;
import com.sapient.wellington.service.GroupingService;

@Component
public class CSVFileReader implements FileReader {

	@Autowired
	GroupingService groupingService;
	@PostConstruct
	public void watchFiles() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Path myDir = Paths.get("D:/data");
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

	@Override
	public void readFile(int noOfFiles, String filePath) throws Exception {
		String[] filesPath = null;
		if (!filePath.isEmpty()) {
			filesPath = filePath.split(",");
		}

		if (filesPath.length != noOfFiles) {
			throw new Exception("no of files are incorrect,program aborting");
		}

		for (int i = 0; i < filesPath.length; i++) {
			File file = new File(filesPath[i]);
			Scanner s = new Scanner(file);
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				Order order = parseCSVToOrder(line);
				System.out.println(order.getOrderId());
			}
		}
	}

	/**
	 * method to parse the content to order domain object
	 * @param line
	 * @return
	 */
	private Order parseCSVToOrder(String line) {
		Order order = null;
		String[] arr = null;
		if (line != null && !line.isEmpty()) {
			try {
				arr = line.split(",");
				order = new Order();
				order.setOrderId(new Integer(arr[0]));
				order.setSecurityId(new Integer(arr[1]));
				order.setTransactionType(TransactionType.valueOf(arr[2]));
				order.setSymbol(arr[3]);
				order.setQuantity(new Integer(arr[4]));
				order.setLimitPrice(new Double(arr[5].toString()));
				order.setOrderInstruction(arr[6]);
			}

			catch (NumberFormatException ex) {
              System.out.println("exception in parsing the data for order"+ex.getMessage());
			}
		}
		return order;
	}

}
