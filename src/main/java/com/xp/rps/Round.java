package com.xp.rps;

import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;

public class Round {
    int roundId;
    Throw throw1;
    Throw throw2;
    Result result;

    public Round() {
    }

    public Round(int roundId, Throw throw1, Throw throw2, Result result) {
        this.roundId = roundId;
        this.throw1 = throw1;
        this.throw2 = throw2;
        this.result = result;
    }

    public Round(Throw throw1, Throw throw2, Result result) {
        this.throw1 = throw1;
        this.throw2 = throw2;
        this.result = result;
    }

    public Round(Throw throw1, Throw throw2) {
        this.throw1 = throw1;
        this.throw2 = throw2;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Throw getThrow1() {
        return throw1;
    }

    public void setThrow1(Throw throw1) {
        this.throw1 = throw1;
    }

    public Throw getThrow2() {
        return throw2;
    }

    public void setThrow2(Throw throw2) {
        this.throw2 = throw2;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
