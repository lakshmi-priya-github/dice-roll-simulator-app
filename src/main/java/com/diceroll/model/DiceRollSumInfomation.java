package com.diceroll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceRollSumInfomation {

	private Integer summation;
	private Integer occurence;
	private Double percentage;
	
}
