package com.xp.rps;

import com.xp.rps.rule.RPS;
import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


//Testing suit
public class RPSTest {
    @Test //one test case
    void rockVsPaper() {



        //alt+enter
        assertEquals(Result.P2_WINS, RPS.play(Throw.ROCK, Throw.PAPER));
    }

    @Test
    void rockVsScissors() {
        assertEquals(Result.P1_WINS,RPS.play(Throw.ROCK, Throw.SCISSORS));

    }

    @Test
    void paperVsScissors() {
        assertEquals(Result.P2_WINS,RPS.play(Throw.PAPER, Throw.SCISSORS));

    }

}
