package com.sklad.testCase;

import org.springframework.boot.SpringApplication;

public class TestTestCaseApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestCaseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
