package com.xp.rps.data;

import com.xp.rps.data.Game;
import com.xp.rps.data.GameResult;
import com.xp.rps.data.Round;
import com.xp.rps.data.RpsRepository;
import com.xp.rps.rule.Result;
import com.xp.rps.rule.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcRepo implements RpsRepository {
    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_GAME = "SELECT * FROM GAME WHERE ID=?";
    private final String SQL_CREATE_GAME = "INSERT INTO GAME (PLAYER1, PLAYER2, ROUND) VALUES (?,?,?)";

    private final String SQL_GET_ROUND = "SELECT * FROM ROUND WHERE GAME_ID=?";
    private final String SQL_CREATE_ROUND = "INSERT INTO ROUND (GAME_ID, THROW1, THROW2, RESULT) VALUES (?,?,?,?)";

    @Autowired
    public JdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createGame(Game g) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_CREATE_GAME,RETURN_GENERATED_KEYS);
            ps.setString(1, g.getPlayer1());
            ps.setString(2, g.getPlayer2());
            ps.setInt(3,g.getRound());
            return ps;
        }, keyHolder);

        //System.out.println("====>"+keyHolder.getKeys());
        return (int)keyHolder.getKeys().get("ID");

    }

    @Override
    public Game getGame(int gameId) {
        Game game =  jdbcTemplate.queryForObject(SQL_GET_GAME, new Object[]{gameId},
                (rs, rowNum) ->
                new Game(
                        rs.getInt("ID"),
                        rs.getString("PLAYER1"),
                        rs.getString("PLAYER2"),
                        rs.getInt("ROUND")
                ));
        return game;
    }

    @Override
    public int saveGameResult(GameResult gr) {
        //insert all the rounds only
        for (int i=0; i<gr.getRoundList().size(); i++) {
            Round r = gr.getRoundList().get(i);
            //Only insert the Round without ID ==> new Round object
            if (r.getRoundId()==0) {
                jdbcTemplate.update(SQL_CREATE_ROUND,
                        gr.getGame().getId(),
                        r.getThrow1().toString(),
                        r.getThrow2().toString(),
                        r.getResult().toString());
            }
        }

        return gr.getGame().getId();
    }

    @Override
    public GameResult getGameResult(int id) {
        Game game = this.getGame(id);

        List<Round> rounds = jdbcTemplate.query(
                SQL_GET_ROUND,
                new Object[]{id},
                mapper);

        GameResult result = new GameResult();
        result.setGame(game);
        result.setRoundList(rounds);

        return result;

    }

    private final RowMapper<Round> mapper = (rs, rowNum) -> new Round(
            rs.getInt("ID"),
            Throw.valueOf(rs.getString("THROW1")),
            Throw.valueOf(rs.getString("THROW2")),
            Result.valueOf(rs.getString("RESULT"))
    );
}
