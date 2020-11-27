package com.diceroll.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.diceroll.DiceRollAppApplication;
import com.diceroll.model.DiceRollResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {DiceRollAppApplication.class})
class DiceRollServiceImplTest {
	@Autowired
	DiceRollService diceRollService;

	@Test
	void testGetSimulationResults() {
		diceRollService.getSimulationResults();
		assertTrue(true);
	}

	
	@Test
	void testRockAndRollNotNull() {
		DiceRollResponse response = new DiceRollResponse();
		response = diceRollService.rockAndRoll(4, 6, 200);
		assertNotNull(response, "Response not null");
		System.out.println(response);
	}
	
	@Test
	void testRockAndRollCount() {
		DiceRollResponse response = new DiceRollResponse();
		int sum=0;
		response = diceRollService.rockAndRoll(3, 6, 100);
		response.getResults().stream().forEach(entry -> {
			//sum=entry.getOccurence().collect(Collectors.summingInt(Integer::intValue));

		});
		assertTrue(sum==100);
	}
}
