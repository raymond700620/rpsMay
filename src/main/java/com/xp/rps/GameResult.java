package com.xp.rps;

import com.xp.rps.rule.Result;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    Game game;
    List<Round> roundList = new ArrayList();
    Result result;

    public GameResult() {
    }

    public GameResult(Game game) {
        this.game = game;
    }

    public void addRound(Round round) {
        roundList.add(round);
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
