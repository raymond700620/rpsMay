package com.xp.rps.data;

import com.xp.rps.data.Game;
import com.xp.rps.data.GameResult;

public interface RpsRepository {
    public int createGame(Game g);

    Game getGame(int gameId);

    public int saveGameResult(GameResult gr);

    GameResult getGameResult(int id);


}
