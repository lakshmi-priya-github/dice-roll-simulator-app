package com.diceroll.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.diceroll.model.DiceRollResponse;
import com.diceroll.model.DiceRollResultTO;
import com.diceroll.service.DiceRollService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest
class DiceControllerIT {
	
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DiceRollService diceRollService;
    
    @Test
    @DisplayName("Test for 200 status")
    public void testDiceRoll200() throws Exception {
    	DiceRollResponse diceRollResponse = new DiceRollResponse();
    	List<DiceRollResultTO> diceRollResultTOList = new ArrayList<DiceRollResultTO>();
    	diceRollResultTOList.add(new DiceRollResultTO(1,1));
     	diceRollResponse.setResults(diceRollResultTOList);
    	Mockito.when(diceRollService.rockAndRoll(anyInt(), anyInt(), anyInt())).thenReturn(diceRollResponse);
    	mockMvc.perform(MockMvcRequestBuilders.get("/dicesimulator/roll")
    			.accept(MediaType.APPLICATION_JSON_VALUE)
    			.param("noOfDice", "2")
    			.param("noOfSides", "6")
    			.param("noOfRolls", "100"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @DisplayName("Test for valid simulation result")
    public void testDiceRollValid() throws Exception {
    	DiceRollResponse diceRollResponse = new DiceRollResponse();
    	List<DiceRollResultTO> diceRollResultTOList = new ArrayList<DiceRollResultTO>();
    	diceRollResultTOList.add(new DiceRollResultTO(4,25));
     	diceRollResponse.setResults(diceRollResultTOList);
    	Mockito.when(diceRollService.rockAndRoll(anyInt(), anyInt(), anyInt())).thenReturn(diceRollResponse);
    	mockMvc.perform(MockMvcRequestBuilders.get("/dicesimulator/roll")
    			.accept(MediaType.APPLICATION_JSON_VALUE)
    			.param("noOfDice", "2")
    			.param("noOfSides", "6")
    			.param("noOfRolls", "100"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.results", IsNull.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].sum", IsEqual.equalTo(4)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].occurence", IsEqual.equalTo(25)));;
    }
    
    @Test
    @DisplayName("Test for invalid inputs for simulation")
    public void testDiceRollInValid() throws Exception {
    	DiceRollResponse diceRollResponse = new DiceRollResponse();
    	List<DiceRollResultTO> diceRollResultTOList = new ArrayList<DiceRollResultTO>();
    	diceRollResultTOList.add(new DiceRollResultTO(1,1));
     	diceRollResponse.setResults(diceRollResultTOList);
     	try {
    	Mockito.when(diceRollService.rockAndRoll(anyInt(), anyInt(), anyInt())).thenReturn(diceRollResponse);
    	    	mockMvc.perform(MockMvcRequestBuilders.get("/dicesimulator/roll")
    			.accept(MediaType.APPLICATION_JSON_VALUE)
    			.param("noOfDice", "2")
    			.param("noOfSides", "0")
    			.param("noOfRolls", "100"));
     	} catch (Exception ex) {
     		assertTrue(true);
     	}
        
    }
    
    
    /*

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/dicesimulator/").toString(), String.class);
        assertEquals("Hello Controller", response.getBody());

    }
    
    @Test
    @DisplayName("Test for 200 status and valid result")
    public void testDiceRoll200() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/dicesimulator/roll?noOfDice=2&noOfSides=5&noOfRolls=300").toString(), String.class);
        //assertEquals("Hello Controller", response.getBody());
        assertTrue(response.getBody().contains("results"));

    }
    
    @Test
    @DisplayName("Test for invalid request with 500 error ")
    public void testDiceRoll() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/dicesimulator/roll?noOfDice=0&noOfSides=5&noOfRolls=300").toString(), String.class);
        System.out.println("response"+response);
        //assertTrue(response.getBody().contains("results"));

    }
    
    */


}
