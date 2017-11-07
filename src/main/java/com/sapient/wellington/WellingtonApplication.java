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

	@Autowired
	private FileReader fileReader;

	private static final Logger logger = LoggerFactory.getLogger(WellingtonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WellingtonApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application started with command-line arguments: {}"+ Arrays.toString(args.getSourceArgs()));
		System.out.println("NonOptionArgs: {}"+ args.getNonOptionArgs());
		System.out.printf("OptionNames: {}", args.getOptionNames());
		fileReader.readFile();
	}

}
