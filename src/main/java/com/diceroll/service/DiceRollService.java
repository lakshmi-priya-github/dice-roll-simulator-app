package com.diceroll.service;

import com.diceroll.model.DiceRollResponse;
import com.diceroll.model.DiceRollSummaryResponse;

public interface DiceRollService {
	
	public DiceRollResponse rockAndRoll(int noOfDice, int sides, int timesRolled );
	public DiceRollSummaryResponse getSimulationResults();

}
