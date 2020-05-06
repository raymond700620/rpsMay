package com.xp.rps;

import com.xp.rps.rule.RPS;
import com.xp.rps.rule.Result;
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
        round.setResult(RPS.play(round.throw1, round.throw2));

        //Store in GameResult
        GameResult gr = repo.getGameResult(id);
        if (gr==null) {
            Game game = repo.getGame(id);
            gr = new GameResult(game);
        }
        gr.addRound(round);
        repo.saveGameResult(gr);

        return round;
    }

    @GetMapping("/gameresult/{id}")
    GameResult getGameResult(@PathVariable int id) {

        return repo.getGameResult(id);
    }

}
