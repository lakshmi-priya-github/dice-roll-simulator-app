package com.diceroll;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceroll.controller.DiceController;

@SpringBootTest
class DiceRollAppApplicationTests {
	@Autowired
	private DiceController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
