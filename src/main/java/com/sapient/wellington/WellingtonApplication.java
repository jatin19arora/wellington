package com.sapient.wellington;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.wellington.filereader.FileReader;

@SpringBootApplication
public class WellingtonApplication implements ApplicationRunner {

	private static final String FILE_PATH = "file.path";

	private static final String FILE_COUNT = "file.count";

	@Autowired
	private FileReader fileReader;

	private static final Logger logger = LoggerFactory.getLogger(WellingtonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WellingtonApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		int noOfFiles = 0;
		String filePath = null;
		for (String name : args.getOptionNames()) {
			if (name.equals(FILE_COUNT)) {
				try {
					noOfFiles = Integer.parseInt(args.getOptionValues(FILE_COUNT).get(0));
				}

				catch (NumberFormatException ex) {
					logger.debug("exception in parsing argument file.path" + ex.getMessage());
				}
			}

			if (name.equals(FILE_PATH)) {
				filePath = args.getOptionValues(FILE_PATH).get(0).toString();
			}

		}
		fileReader.readFile(noOfFiles, filePath);
	}
}
