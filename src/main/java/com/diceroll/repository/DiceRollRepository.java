package com.diceroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiceRollRepository extends CrudRepository<com.diceroll.entity.DiceRoll, Long> {

}
