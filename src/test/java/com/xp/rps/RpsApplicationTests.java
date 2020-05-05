package com.xp.rps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RpsApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void createGame() {
		ResponseEntity<Integer> response = restTemplate.postForEntity("/game", new Game("Raymond","Rae",3), Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		int id = response.getBody();
		assertTrue(id>0);


		ResponseEntity<Game> result = restTemplate.getForEntity("/game/"+id, Game.class);
		assertEquals("Raymond", result.getBody().getPlayer1());
	}


}


//RPS class, which will return who wins? whenever there are two input