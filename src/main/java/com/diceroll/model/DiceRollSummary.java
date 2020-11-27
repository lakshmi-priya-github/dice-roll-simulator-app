package com.diceroll.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DiceRollSummary {
	
	private Integer totalRolls;
	
	private List<DiceRollSumInfomation> results = new ArrayList<>();

}
