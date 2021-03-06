CREATE TABLE GAME (
  ID         SMALLINT GENERATED ALWAYS AS IDENTITY,
  PLAYER1 VARCHAR(200),
  PLAYER2    VARCHAR(200),
  ROUND       SMALLINT,

  PRIMARY KEY (ID)
)

#INSERT INTO GAME (PLAYER1, PLAYER2, ROUND) VALUES ('RAYMOND','RAE',2)

CREATE TABLE ROUND (
  ID         SMALLINT GENERATED ALWAYS AS IDENTITY,
  GAME_ID    SMALLINT,
  THROW1 VARCHAR(200),
  THROW2    VARCHAR(200),
  RESULT       VARCHAR(200),

  PRIMARY KEY (ID)
)

#INSERT INTO ROUND (GAME_ID, THROW1, THROW2, RESULT) VALUES (1, 'PAPER','ROCK','P1_WINS')

#Create a DB

#1. Test DB and Dev DB suppose seperate
#2. Test Result suppose remove
#3. URL suppose to be done in property or bootRun
#4. due to some reason, the Elephone DB connection issue from GitAction so disabled, but @SpringbootTest is successful