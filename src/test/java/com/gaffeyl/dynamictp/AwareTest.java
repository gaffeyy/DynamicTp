package com.gaffeyl.dynamictp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AwareTest {

	@Test
	void getEnvironment() {
		Aware aware = new Aware();
		Environment environment = aware.getEnvironment();
		String[] activeProfiles = environment.getActiveProfiles();
		for(String s:activeProfiles){
			System.out.println(s);
		}
	}
}