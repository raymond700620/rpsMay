package com.xp.rps;

import com.xp.rps.data.*;
import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//UNIT Test
public class JDBCRepoTest {

    RpsRepository repo;

    @BeforeEach
    void setup() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        //new PGConnectionPoolDataSource()

        dataSource.setURL("jdbc:postgresql://arjuna.db.elephantsql.com:5432/ffptppgf?user=ffptppgf&password=R9TY-k4fr6oMdEOGnS-kBqkVHb1gzY4q");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        repo = new JdbcRepo(jdbcTemplate);
    }


    @Test
    @Disabled
    void getGame() {
        Game g = new Game("p1","p2",3);
        int id = repo.createGame(g);

        g = repo.getGame(id);
        System.out.println(g.getPlayer2());
        assertEquals("p1",g.getPlayer1());

        //Game Result test
        GameResult gameResult = new GameResult(g);
        Round r = new Round(Throw.ROCK, Throw.PAPER, Result.P2_WINS);
        gameResult.addRound(r);
        r = new Round(Throw.PAPER, Throw.SCISSORS, Result.P2_WINS);
        gameResult.addRound(r);
        r = new Round(Throw.SCISSORS, Throw.ROCK, Result.P2_WINS);
        gameResult.addRound(r);

        int gameId = repo.saveGameResult(gameResult);

        GameResult result = repo.getGameResult(gameId);
        assertEquals(3, result.getRoundList().size());
        System.out.println("====>"+result);

        //test saveGameResult by adding new Round
        r = new Round(Throw.PAPER, Throw.ROCK, Result.P1_WINS);
        result.addRound(r);
        r = new Round(Throw.SCISSORS, Throw.ROCK, Result.P2_WINS);
        result.addRound(r);

        repo.saveGameResult(result);

        result = repo.getGameResult(gameId);
        assertEquals(5, result.getRoundList().size());

       // System.out.println("======>"+gameResult.getRoundList().size());


    }



}
