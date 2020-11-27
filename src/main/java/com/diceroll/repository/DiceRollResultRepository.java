package com.diceroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diceroll.entity.DiceRollResult;

@Repository
public interface DiceRollResultRepository extends CrudRepository<DiceRollResult, Long> {

	@Query( nativeQuery = true, 
			value = "SELECT sum,  sum(occurence) totalOccurence," + 
					"   concat(no_of_dice,'-dice-',  no_of_sides,'-sides') combination, " + 
					"   (select sum(no_of_rolls) from dice_roll r where r.no_of_dice=d.no_of_dice and r.no_of_sides = d.no_of_sides) rollCount " + 
					"FROM  dice_roll_result dr JOIN  dice_roll d ON d.id = dr.dice_roll_id GROUP BY no_of_sides, no_of_dice, dr.sum")
	public List<Object[]> getSimulationResult();
}
