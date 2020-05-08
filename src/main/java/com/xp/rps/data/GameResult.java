package com.xp.rps.data;

import com.xp.rps.rule.Result;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    Game game;
    List<Round> roundList = new ArrayList();

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

    //Calculate on the fly
    public Result getResult() {
        int p1=0;
        int p2=0;
        Result result;
        for(Round round: roundList) {
            if (Result.P1_WINS.equals(round.getResult())) {
                p1++;
            }
            else if (Result.P2_WINS.equals(round.getResult())) {
                p2++;
            }
        }
        if (p1>p2) result=Result.P1_WINS;
        else if (p2>p1) result=Result.P2_WINS;
        else result = Result.DRAW;
        return result;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "game=" + game +
                ", roundList=" + roundList +
                '}';
    }
}
