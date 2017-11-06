package com.sapient.wellington;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.wellington.filereader.FileReader;

@SpringBootApplication
public class WellingtonApplication implements CommandLineRunner {

	@Autowired
	private FileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(WellingtonApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		fileReader.readFile();
	}

}
