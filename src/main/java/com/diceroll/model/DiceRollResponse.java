package com.diceroll.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class DiceRollResponse {

	private List<DiceRollResultTO> results= new ArrayList<>();
}
