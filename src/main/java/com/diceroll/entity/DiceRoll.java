package com.diceroll.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "dice_roll")
public class DiceRoll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private int noOfDice;

	private int noOfSides;

	private int noOfRolls;

	@OneToMany(mappedBy = "diceRoll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DiceRollResult> results;
}
