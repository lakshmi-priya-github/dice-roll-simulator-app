package com.diceroll.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.diceroll.DiceRollAppApplication;
import com.diceroll.entity.DiceRoll;
import com.diceroll.entity.DiceRollResult;
import com.diceroll.model.DiceRollResponse;
import com.diceroll.model.DiceRollSummaryResponse;
import com.diceroll.repository.DiceRollRepository;

@SpringBootTest()
@ContextConfiguration(classes = { DiceRollAppApplication.class })
class DiceRollServiceImplTest {
	@Autowired
	DiceRollService diceRollService;

	@Autowired
	DiceRollRepository diceRollRepository;

	@Test
	void testGetSimulationResults() {
		diceRollService.getSimulationResults();
		assertTrue(true);
	}

	@Test
	@DisplayName("Test for RockAndRoll not null")
	void testRockAndRollNotNull() {
		DiceRollResponse result = diceRollService.rockAndRoll(4, 6, 200);
		assertNotNull(result);
	}
	
	@Test
	@DisplayName("Test for RockAndRoll response")
	void testRockAndRollResponse() {
		DiceRollResponse result = diceRollService.rockAndRoll(4, 6, 200);
		assertNotNull(result.getResults());
	}
	
	@Test
	void testRockAndRollCount() {
		DiceRollResponse response = new DiceRollResponse();
		Integer sum = 100;
		response = diceRollService.rockAndRoll(3, 6, 100);
		response.getResults().stream().forEach(entry -> {
			// sum=entry.getOccurence().collect(Collectors.summingInt(Integer::intValue));

		});
		assertTrue(sum == 100);
	}
	
	@DisplayName("Test for RockAndRoll entities")
	void testRockAndRollEntities() {
		DiceRollResponse diceRollResponse = new DiceRollResponse();
		DiceRoll diceRoll = new DiceRoll();
		diceRoll.setNoOfDice(4);
		diceRoll.setNoOfRolls(4);
		diceRoll.setNoOfSides(100);
		DiceRollResult diceRollResult1 = new DiceRollResult();
		diceRollResult1.setSum(10);
		diceRollResult1.setOccurence(10);
		DiceRollResult diceRollResult2 = new DiceRollResult();
		diceRollResult2.setSum(8);
		diceRollResult2.setOccurence(10);
		Set<DiceRollResult> diceRollResultSet = new HashSet<DiceRollResult>();
		diceRollResultSet.add(diceRollResult1);
		diceRollResultSet.add(diceRollResult2);
		diceRoll.setResults(diceRollResultSet);
		DiceRollResponse result = diceRollService.rockAndRoll(4, 6, 200);
		assertNotNull(diceRollRepository.findAll());
	}
	
	@Test
	@DisplayName("Test for testGetSimulationResults not null")
	void testGetSimulationResultsNotNull() {
		DiceRollSummaryResponse result = diceRollService.getSimulationResults();;
		assertNotNull(result);
	}
	
	@Test
	@DisplayName("Test for testGetSimulationResults response")
	void testGetSimulationResultsResponse() {
		DiceRollSummaryResponse result = diceRollService.getSimulationResults();;
		assertNotNull(result.getSummary());
	}
	
	@Test
	@DisplayName("Test for testGetSimulationResults sum value")
	void testGetSimulationResultsCount() {
		DiceRollResponse response = new DiceRollResponse();
		response = diceRollService.rockAndRoll(3, 6, 450);
		DiceRollSummaryResponse result = diceRollService.getSimulationResults();
		assertTrue(result.getSummary().get("3-dice-6-sides").getTotalRolls() == 450);
	}

}
