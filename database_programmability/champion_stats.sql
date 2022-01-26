USE [lolproject]
GO

/****** Object:  UserDefinedFunction [dbo].[championStats]    Script Date: 26.01.2022 01:03:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER function [dbo].[championStats](
        @champName varchar(100)
    )
    returns
    @temptable2 table
    (
        champion varchar(50),
        win_ratio double precision,
		games int
	)
    as
    begin
        declare @match_id varchar(30), @team_id int
		declare @temptable table
		(
			champion varchar(50),
			icon_url varchar(255),
			match_id varchar(255),
			isWin bit
		)
        declare cur cursor for
            select m.match_id, Team_team_id
            from Match_participant
                     inner join Match M on Match_participant.Match_match_id = M.match_id
            where champion_name = @champName
        open cur
        fetch next from cur into @match_id, @team_id
            while @@FETCH_STATUS = 0
                begin
                    insert into @temptable
                    select champion_name, icon_url, match_id, Match_participant.win
                    from Match_participant
                        inner join Champion C
                            on Match_participant.champion_id = C.champion_id
                        inner join Match M2
                            on Match_participant.Match_match_id = M2.match_id
                    where M2.match_id = @match_id and Team_team_id <> @team_id
                    fetch next from cur into @match_id, @team_id
                end
        close cur
        deallocate cur

		DECLARE @Table1 TABLE (
			champion varchar(50),
			victories double precision
		)
		insert into @Table1
		select champion, count(*) 'victories'
		from @temptable
		where isWin = 1
		group by champion

		DECLARE @Table2 TABLE (
			champion varchar(50),
			defeats double precision
		)
		insert into @Table2
		select champion, count(*) 'defeats'
		from @temptable
		where isWin = 0
		group by champion
		
		DECLARE @ResultTable TABLE (
			champion varchar(50),
			victories double precision,
			defeats double precision
		)
		insert into @ResultTable
		SELECT ISNULL(T1.champion, T2.champion) AS champion, 
		ISNULL(T1.victories, 0) AS Value1, 
		ISNULL(T2.defeats, 0) AS Value2
		FROM @Table1 T1
		FULL OUTER JOIN @Table2 T2 ON T2.champion = T1.champion
		
		insert into @temptable2
		select champion, (victories/(victories+defeats)) 'win_ratio', (victories+defeats) 'games'
		from @ResultTable
		where (victories + defeats > 3)
        return
    end
GO

