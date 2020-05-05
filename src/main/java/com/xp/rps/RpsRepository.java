package com.xp.rps;

public interface RpsRepository {
    public int createGame(Game g);

    Game getGame(int gameId);

    public int saveGameResult(GameResult gr);

    GameResult getGameResult(int id);


}
