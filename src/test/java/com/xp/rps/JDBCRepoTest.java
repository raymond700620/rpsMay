package com.xp.rps;

import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JDBCRepoTest {


    static JdbcRepo repo;

    @BeforeAll
    static void setup() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL("jdbc:postgresql://arjuna.db.elephantsql.com:5432/ffptppgf?user=ffptppgf&password=R9TY-k4fr6oMdEOGnS-kBqkVHb1gzY4q");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        repo = new JdbcRepo(jdbcTemplate);
    }


    @Test
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
    }



}
