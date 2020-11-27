package com.diceroll.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.diceroll.DiceRollAppApplication;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {DiceRollAppApplication.class})
class DiceControllerIT {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/dicesimulator/").toString(), String.class);
        assertEquals("Hello Controller", response.getBody());

    }
    
    @Test
    public void testDiceRoll() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/dicesimulator/roll?noOfDice=2&noOfSides=5&noOfRolls=300").toString(), String.class);
        //assertEquals("Hello Controller", response.getBody());
        assertTrue(response.getBody().contains("results"));

    }


}
