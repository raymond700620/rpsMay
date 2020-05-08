package com.xp.rps;

import com.xp.rps.data.Game;
import com.xp.rps.data.GameResult;
import com.xp.rps.data.Round;
import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;
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
	void OneGame() {
		//1. create a game
		ResponseEntity<Integer> response = restTemplate.postForEntity("/game", new Game("Raymond","Rae",3), Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		int id = response.getBody();
		assertTrue(id>0);


		ResponseEntity<Game> result = restTemplate.getForEntity("/game/"+id, Game.class);
		assertEquals("Raymond", result.getBody().getPlayer1());

		//2. play Round
		ResponseEntity<Round> response2 = restTemplate.postForEntity("/play/"+id, new Round(Throw.ROCK, Throw.PAPER), Round.class);
		assertEquals(Result.P2_WINS, response2.getBody().getResult());

		ResponseEntity<Round> response3 = restTemplate.postForEntity("/play/"+id, new Round(Throw.ROCK, Throw.SCISSORS), Round.class);
		assertEquals(Result.P1_WINS, response3.getBody().getResult());

		ResponseEntity<Round> response4 = restTemplate.postForEntity("/play/"+id, new Round(Throw.ROCK, Throw.PAPER), Round.class);
		assertEquals(Result.P2_WINS, response4.getBody().getResult());


		//3. getGameResult
		ResponseEntity<GameResult> response10 = restTemplate.getForEntity("/gameresult/"+id, GameResult.class);
		assertEquals(3, response10.getBody().getRoundList().size());
		assertEquals(Result.P2_WINS, response10.getBody().getRoundList().get(0).getResult());
		assertEquals(Result.P2_WINS, response10.getBody().getResult());

	}





}


//RPS class, which will return who wins? whenever there are two input