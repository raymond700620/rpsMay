package com.xp.rps;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryRepo implements RpsRepository {
    HashMap<Integer, Game> gameRepo = new HashMap<>();
    int sequence = 0;

    @Override
    public int createGame(Game g) {
        int id = ++sequence;
        g.setId(id);
        gameRepo.put(id, g);
        return id;
    }

    @Override
    public Game getGame(int gameId) {
        return gameRepo.get(gameId);
    }


}
