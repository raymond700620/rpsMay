package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class RpsController {
    RpsRepository repo;

    @Autowired
    RpsController(RpsRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/game")
    int create(@RequestBody Game game) {
        int id = repo.create(game);
        return id;
    }

    @GetMapping("/game/{id}")
    Game getGame(@PathVariable int id) {
       return repo.get(id);
    }
}
