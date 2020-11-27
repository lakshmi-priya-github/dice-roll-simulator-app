package com.diceroll.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diceroll.model.DiceRollResponse;
import com.diceroll.model.DiceRollSummaryResponse;
import com.diceroll.service.DiceRollService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@EnableAutoConfiguration
@Validated
@RequestMapping("dicesimulator")
public class DiceController {

	@Autowired
	DiceRollService diceRollService;

	@Operation(summary = "Simulate dice roll.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Get dice roll results.", 
					content = @Content(schema = @Schema(implementation = DiceRollResponse.class))),
			})
	@GetMapping(path = "roll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> diceRoll(
			@Valid @RequestParam("noOfDice") @NotNull @Min(value = 1, message = "The number of dice must be at least 1") Integer noOfDice,
			@Valid @RequestParam("noOfSides") @NotNull @Min(value = 4, message = "The sides of a dice must be at least 4") @Max(value = 6, message = "The sides of a dice must be maximum 6") Integer noOfSides,
			@Valid @RequestParam("noOfRolls") @NotNull @Min(value = 1, message = "The number of rolls must be at least 1") Integer noOfRolls) {

		return ResponseEntity.ok(diceRollService.rockAndRoll(noOfDice, noOfSides, noOfRolls));
	}

	@Operation(summary = "Report on simulation of dice roll.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Get dice roll results report grouped by no of dice and no of slides.",
					content = @Content(schema = @Schema(implementation = DiceRollSummaryResponse.class))),
			})
	@GetMapping(path = "getsimulationresults", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSimulationResults() {
		return ResponseEntity.ok(diceRollService.getSimulationResults());
	}
	
	@ResponseBody
    @GetMapping("/")
    public String hello() {
        return "Hello Controller";
    }

}
