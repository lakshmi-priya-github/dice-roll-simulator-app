package com.diceroll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiceRollResultTO {
	
	private Integer sum;
	private Integer occurence;

}
