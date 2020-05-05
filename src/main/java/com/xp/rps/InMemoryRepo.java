package com.xp.rps;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryRepo implements RpsRepository {
    HashMap<Integer, Game> repo = new HashMap<>();
    int sequence = 0;

    @Override
    public int create(Game g) {
        int id = ++sequence;
        g.setId(id);
        repo.put(id, g);
        return id;
    }

    @Override
    public Game get(int gameId) {
        return repo.get(gameId);
    }


}
