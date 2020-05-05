package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RpsRepositoryTest {
    RpsRepository repo;

    @BeforeEach
    void setup() {
        repo = new InMemoryRepo();
    }

    @Test
    void createGame() {
        Game g = new Game("Raymond","Rae",3);
        int gameId = repo.create(g);
        g = repo.get(gameId);
        assertEquals("Raymond", g.getPlayer1());
    }
}
