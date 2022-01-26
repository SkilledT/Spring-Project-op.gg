USE [lolproject]
GO

/****** Object:  StoredProcedure [dbo].[COUNT_KILL_PARTICIPATION]    Script Date: 26.01.2022 01:02:25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER PROCEDURE [dbo].[COUNT_KILL_PARTICIPATION]
    (@GAME_ID NVARCHAR(100), @SUMMONER_NICKNAME NVARCHAR(100))
    AS
    BEGIN
        DECLARE @SUMMONER_ID VARCHAR(100);
        SELECT @SUMMONER_ID = summoner_id FROM Summoner WHERE summoner_nickname = @SUMMONER_NICKNAME;
        DECLARE @TEAM_ID VARCHAR(100);
        SELECT @TEAM_ID = Team_team_id FROM Match_participant WHERE Summoner_summoner_id = @SUMMONER_ID;

        DECLARE @KILLS double precision;
        DECLARE @ASSISTS double precision;

        DECLARE @SUMMONER_KILLS DOUBLE PRECISION;
        DECLARE @SUMMONER_ASSISTS DOUBLE PRECISION;

        DECLARE @KILL_PARTICIPATION DOUBLE PRECISION;

        SELECT @KILLS = SUM(kills),  @ASSISTS = SUM(assists)
        FROM Match_participant
            INNER JOIN Match M2 on Match_participant.Match_match_id = M2.match_id
        WHERE M2.match_id = @GAME_ID AND Team_team_id = @TEAM_ID;

        SELECT @SUMMONER_KILLS = KILLS, @SUMMONER_ASSISTS = assists
        FROM  Match_participant
            INNER JOIN Match M3 on Match_participant.Match_match_id = M3.match_id
        WHERE M3.match_id = @GAME_ID AND Summoner_summoner_id = @SUMMONER_ID;

        SET @KILL_PARTICIPATION = (@SUMMONER_KILLS + @SUMMONER_ASSISTS)/(@KILLS + @ASSISTS);
        print @KILL_PARTICIPATION;
    END
GO

