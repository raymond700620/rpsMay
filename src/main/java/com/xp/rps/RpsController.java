package com.xp.rps;

import com.xp.rps.data.Game;
import com.xp.rps.data.GameResult;
import com.xp.rps.data.Round;
import com.xp.rps.data.RpsRepository;
import com.xp.rps.rule.RPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RpsController {
    RpsRepository repo;

    @Autowired
    RpsController(RpsRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    String hello() {
        return "Hello";
    }

    @PostMapping("/game")
    int create(@RequestBody Game game) {
        int id = repo.createGame(game);
        return id;
    }

    @GetMapping("/game/{id}")
    Game getGame(@PathVariable int id) {
       return repo.getGame(id);
    }

    @PostMapping("/play/{id}")
    Round play(@PathVariable int id, @RequestBody Round round) {
       // round.setResult(RPS.play(round.throw1, round.throw2));
        round.setResult(RPS.play(round.getThrow1(), round.getThrow2()));

        //TODO: change to only insert the comming new Round
        //12 factors
        //MS Stateless state --> 1. Inmemory, 2. Filesystem
        //Store in GameResult
        //fetch a gameResult with 2 rounds from DB
        GameResult gr = repo.getGameResult(id);
        if (gr==null) {
            Game game = repo.getGame(id);
            gr = new GameResult(game);
        }
        gr.addRound(round); //by adding a new round, it is 3 rounds for this result
        repo.saveGameResult(gr);

        return round;
    }

    @GetMapping("/gameresult/{id}")
    GameResult getGameResult(@PathVariable int id) {

        return repo.getGameResult(id);
    }

}
