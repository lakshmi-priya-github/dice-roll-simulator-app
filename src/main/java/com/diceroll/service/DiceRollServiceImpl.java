package com.diceroll.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diceroll.entity.DiceRoll;
import com.diceroll.entity.DiceRollResult;
import com.diceroll.model.DiceRollResponse;
import com.diceroll.model.DiceRollResultTO;
import com.diceroll.model.DiceRollSumInfomation;
import com.diceroll.model.DiceRollSummary;
import com.diceroll.model.DiceRollSummaryResponse;
import com.diceroll.repository.DiceRollRepository;
import com.diceroll.repository.DiceRollResultRepository;

@Service
public class DiceRollServiceImpl implements DiceRollService {

	@Autowired
	private DiceRollResultRepository diceRollResultRepository;

	@Autowired
	private DiceRollRepository diceRollRepository;

	@Override
	public DiceRollResponse rockAndRoll(int noOfDice, int noOfSides, int noOfRolls) {
		DiceRollResponse response = new DiceRollResponse();
		DiceRoll diceRoll = createDiceRoll(noOfDice, noOfSides, noOfRolls);
		diceRollRepository.save(diceRoll);
		List<DiceRollResult> entities = new ArrayList<>();
		Map<Integer, Integer> results = getSimulationResults(noOfDice, noOfSides, noOfRolls);
		results.entrySet().stream().forEach(entry -> {
			int sum = entry.getKey();
			int occurence = entry.getValue();
			entities.add(createEntity(diceRoll, sum, occurence));
			response.getResults().add(new DiceRollResultTO(sum, occurence));
		});
		diceRollResultRepository.saveAll(entities);
		return response;
	}

	@Override
	public DiceRollSummaryResponse getSimulationResults() {
		List<Object[]> results = diceRollResultRepository.getSimulationResult();
		
		DiceRollSummaryResponse response = new DiceRollSummaryResponse();
		for(Object[] objArray : results) {
			Integer summation = (Integer)objArray[0];
			BigInteger totalOccurence = (BigInteger)objArray[1];
			String combination = (String)objArray[2];
			BigInteger rollCount = (BigInteger)objArray[3];
			Double percentage = (double) (totalOccurence.doubleValue()/rollCount.doubleValue()*100);
			DiceRollSummary diceRollSummary = response.getSummary().get(combination);
			if(diceRollSummary == null) {
				diceRollSummary = new DiceRollSummary();
				diceRollSummary.setTotalRolls(rollCount.intValue());
				response.getSummary().put(combination, diceRollSummary);
			}
			diceRollSummary.getResults().add(new DiceRollSumInfomation(summation,totalOccurence.intValue(),percentage));
		}
		return response;
	}

	private DiceRollResult createEntity(DiceRoll diceRoll, int sum, int occurence) {
		DiceRollResult entity = new DiceRollResult();
		entity.setDiceRoll(diceRoll);
		entity.setSum(sum);
		entity.setOccurence(occurence);
		return entity;
	}

	private DiceRoll createDiceRoll(int noOfDice, int noOfSides, int noOfRolls) {
		DiceRoll diceRoll = new DiceRoll();
		diceRoll.setNoOfDice(noOfDice);
		diceRoll.setNoOfSides(noOfSides);
		diceRoll.setNoOfRolls(noOfRolls);
		return diceRoll;
	}

	private Map<Integer, Integer> getSimulationResults(int noOfDice, int noOfSides, int noOfRolls) {
		// Inclusive
		int minRange = 1;
		// Exclusive
		int maxRange = noOfSides + 1;
		Map<Integer, Integer> noOfSimilarResults = new ConcurrentHashMap<>();
		IntStream.range(0, noOfRolls).parallel().forEach(rollIndex -> {
			int sum = ThreadLocalRandom.current().ints(noOfDice, minRange, maxRange).sum();
			noOfSimilarResults.merge(sum, 1, Integer::sum);
		});
		return noOfSimilarResults;
	}
}
