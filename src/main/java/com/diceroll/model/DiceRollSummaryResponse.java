package com.diceroll.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DiceRollSummaryResponse {
	
	private Map<String, DiceRollSummary> summary = new HashMap<>();

}
