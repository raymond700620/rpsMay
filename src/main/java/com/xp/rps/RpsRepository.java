package com.xp.rps;

public interface RpsRepository {
    public int create(Game g);

    Game get(int gameId);
}
