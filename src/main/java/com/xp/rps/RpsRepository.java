package com.xp.rps;

public interface RpsRepository {
    public int createGame(Game g);

    Game getGame(int gameId);
}
