package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InmemoryRepositoryTest {
    RpsRepository repo;

    @BeforeEach
    void setup() {
        repo = new InMemoryRepo();
    }

    @Test
    void createGame() {
        Game g = new Game("Raymond","Rae",3);
        int gameId = repo.createGame(g);
        g = repo.getGame(gameId);
        assertEquals("Raymond", g.getPlayer1());
    }
}
