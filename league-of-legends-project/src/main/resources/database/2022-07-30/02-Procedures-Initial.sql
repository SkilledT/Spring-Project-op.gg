--liquibase formatted sql
--changeset DamianWieczorek:1

/****** Object:  UserDefinedFunction [dbo].[championStats]    Script Date: 15.09.2022 23:25:31 ******/
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
                        champion  varchar(50),
                        win_ratio double precision,
                        games     int
                    )
as
begin
    declare @match_id varchar(30), @team_id int
    declare @temptable table
                       (
                           champion varchar(50),
                           icon_url varchar(255),
                           match_id varchar(255),
                           isWin    bit
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
            where M2.match_id = @match_id
              and Team_team_id <> @team_id
            fetch next from cur into @match_id, @team_id
        end
    close cur
    deallocate cur

    DECLARE @Table1 TABLE
                    (
                        champion  varchar(50),
                        victories double precision
                    )
    insert into @Table1
    select champion, count(*) 'victories'
    from @temptable
    where isWin = 1
    group by champion

    DECLARE @Table2 TABLE
                    (
                        champion varchar(50),
                        defeats  double precision
                    )
    insert into @Table2
    select champion, count(*) 'defeats'
    from @temptable
    where isWin = 0
    group by champion

    DECLARE @ResultTable TABLE
                         (
                             champion  varchar(50),
                             victories double precision,
                             defeats   double precision
                         )
    insert into @ResultTable
    SELECT ISNULL(T1.champion, T2.champion) AS champion,
           ISNULL(T1.victories, 0)          AS Value1,
           ISNULL(T2.defeats, 0)            AS Value2
    FROM @Table1 T1
             FULL OUTER JOIN @Table2 T2 ON T2.champion = T1.champion

    insert into @temptable2
    select champion, (victories / (victories + defeats)) 'win_ratio', (victories + defeats) 'games'
    from @ResultTable
    where (victories + defeats > 3)
    return
end
GO
/****** Object:  UserDefinedFunction [dbo].[championWinRatio]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER function [dbo].[championWinRatio](
    @champName varchar(100)
)
    returns double precision
as
begin
    declare @wins double precision, @games double precision
    select @wins = count(*)
    from Match_participant
    where champion_name = @champName
      and win = 1
    select @games = count(*)
    from Match_participant
    where champion_name = @champName
    return (@wins / @games) * 100
end
GO
/****** Object:  UserDefinedFunction [dbo].[mostPopularPerTreeForChampion]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER function [dbo].[mostPopularPerTreeForChampion](
    @champName varchar(30)
)
    returns int
as
begin
    return (
        select top 1 p.tree_number
        from Match_participant
                 inner join Match_Participant_Perk MPP
                            on Match_participant.Summoner_summoner_id = MPP.participant_match_summoner_id
                                and Match_participant.Match_match_id = MPP.participant_match_match_id
                 inner join Perk P
                            on MPP.perk_id = P.id
        where champion_name = @champName
        group by p.tree_number
        having count(*) >= ALL (
            select count(*)
            from Match_participant
                     inner join Match_Participant_Perk M
                                on Match_participant.Summoner_summoner_id = M.participant_match_summoner_id
                                    and Match_participant.Match_match_id = M.participant_match_match_id
                     inner join Perk P2
                                on M.perk_id = P2.id
            where champion_name = @champName
            group by P2.tree_number
        )
    )
end
GO
/****** Object:  UserDefinedFunction [dbo].[perkMainTree]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER function [dbo].[perkMainTree](@champion_name varchar(50),
                                              @most_occurring_tree int)
    returns table
        return
            (
                select *
                from Perk
                where name in (
                    select top 4 p.name
                    from Match_participant
                             inner join Match_Participant_Perk MPP
                                        on Match_participant.Summoner_summoner_id = MPP.participant_match_summoner_id
                                            and Match_participant.Match_match_id = MPP.participant_match_match_id
                             inner join Perk P
                                        on MPP.perk_id = P.id
                    where champion_name = @champion_name
                      and tree_number = @most_occurring_tree
                    group by slot_number, p.name
                    order by count(*) desc
                )
            )
GO
/****** Object:  UserDefinedFunction [dbo].[perkSubTree]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER function [dbo].[perkSubTree](@champion_name varchar(50),
                                             @most_occurring_tree int)
    returns table
        return
            (
                select *
                from perk
                where name in (
                    select top 2 p.name
                    from Match_participant
                             inner join Match_Participant_Perk MPP
                                        on Match_participant.Summoner_summoner_id = MPP.participant_match_summoner_id
                                            and Match_participant.Match_match_id = MPP.participant_match_match_id
                             inner join Perk P
                                        on MPP.perk_id = P.id
                    where champion_name = @champion_name
                      and p.slot_number <> 0
                      and tree_number = (
                        select top 1 p.tree_number
                        from Match_participant
                                 inner join Match_Participant_Perk MPP
                                            on Match_participant.Summoner_summoner_id =
                                               MPP.participant_match_summoner_id
                                                and Match_participant.Match_match_id = MPP.participant_match_match_id
                                 inner join Perk P
                                            on MPP.perk_id = P.id
                        where champion_name = @champion_name
                          and P.tree_number <> @most_occurring_tree
                        group by p.tree_number
                        having count(*) >= ALL (
                            select count(*)
                            from Match_participant
                                     inner join Match_Participant_Perk M
                                                on Match_participant.Summoner_summoner_id =
                                                   M.participant_match_summoner_id
                                                    and Match_participant.Match_match_id = M.participant_match_match_id
                                     inner join Perk P2
                                                on M.perk_id = P2.id
                            where champion_name = @champion_name
                              and P2.tree_number <> @most_occurring_tree
                            group by P2.tree_number
                        )
                    )
                    group by p.slot_number, p.name
                )
            )
GO
/****** Object:  StoredProcedure [dbo].[champion_with_win_ratio]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER procedure [dbo].[champion_with_win_ratio](@championName varchar(100), @minimumMatches int = 3)
as
begin
    SET NOCOUNT ON
    drop table if exists Champion_With_Win_Ratio_Entity
    CREATE table Champion_With_Win_Ratio_Entity
    (
        name      varchar(100),
        win_ratio double precision,
        icon_url  varchar(255),
        games     int
    )
    insert into Champion_With_Win_Ratio_Entity
    select coalesce(c.name, x.champion) as name,
           coalesce(x.win_ratio, -1)    as win_ratio,
           coalesce(c.icon_url, '')     as icon_url,
           coalesce(x.games, 0)         as games
    from Champion c
             full outer join (select * from [dbo].championStats(@championName)) x
                             on name = champion
    where win_ratio > -1
      and games > @minimumMatches

    select * from Champion_With_Win_Ratio_Entity
end
GO
/****** Object:  StoredProcedure [dbo].[COUNT_KILL_PARTICIPATION]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[COUNT_KILL_PARTICIPATION](@GAME_ID NVARCHAR(100), @SUMMONER_NICKNAME NVARCHAR(100))
AS
BEGIN
    DECLARE @SUMMONER_ID VARCHAR(100)
    SELECT @SUMMONER_ID = summoner_id FROM Summoner WHERE summoner_nickname = @SUMMONER_NICKNAME
    DECLARE @TEAM_ID VARCHAR(100)
    SELECT @TEAM_ID = Team_team_id FROM Match_participant WHERE Summoner_summoner_id = @SUMMONER_ID

    DECLARE @KILLS double precision
    DECLARE @ASSISTS double precision

    DECLARE @SUMMONER_KILLS DOUBLE PRECISION
    DECLARE @SUMMONER_ASSISTS DOUBLE PRECISION

    DECLARE @KILL_PARTICIPATION DOUBLE PRECISION

    SELECT @KILLS = SUM(kills), @ASSISTS = SUM(assists)
    FROM Match_participant
             INNER JOIN Match M2 on Match_participant.Match_match_id = M2.match_id
    WHERE M2.match_id = @GAME_ID
      AND Team_team_id = @TEAM_ID

    SELECT @SUMMONER_KILLS = KILLS, @SUMMONER_ASSISTS = assists
    FROM Match_participant
             INNER JOIN Match M3 on Match_participant.Match_match_id = M3.match_id
    WHERE M3.match_id = @GAME_ID
      AND Summoner_summoner_id = @SUMMONER_ID

    SET @KILL_PARTICIPATION = (@SUMMONER_KILLS + @SUMMONER_ASSISTS) / (@KILLS + @ASSISTS)
    print @KILL_PARTICIPATION
END
GO
/****** Object:  StoredProcedure [dbo].[fillChampionStats]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER procedure [dbo].[fillChampionStats]
as
begin
    -- fill Champion_Stats table
    declare @champName varchar(100)
    declare champion_cursor cursor for select distinct name from Champion
    open champion_cursor
    fetch next from champion_cursor into @champName
    while @@FETCH_STATUS = 0
        begin
            exec updateChampionStats @champName = @champName
            fetch next from champion_cursor into @champName
        end
    close champion_cursor
    deallocate champion_cursor
end
GO
/****** Object:  StoredProcedure [dbo].[get_most_popular_items_for_champion]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER procedure [dbo].[get_most_popular_items_for_champion](@championName nvarchar(100))
as
begin
    SET NOCOUNT ON
    declare @result_item_table table
                               (
                                   item_id     int,
                                   icon_url    nvarchar(max),
                                   plain_text  nvarchar(max),
                                   total_cost  int,
                                   sell        int,
                                   base_cost   int,
                                   description nvarchar(max),
                                   name        nvarchar(max)
                               )

    -- get full items table, not components
    declare @full_items table
                        (
                            name       nvarchar(max),
                            total_cost int
                        )

    insert into @full_items
    select name, total_cost
    from Item
    where name in (
        select distinct I.name
        from Match_participant
                 inner join participant_items
                            on Match_participant.Summoner_summoner_id = participant_items.summoner_id
                                and Match_participant.Match_match_id = participant_items.match_id
                 inner join Item I on participant_items.item_id = I.item_id
                 full outer join Item_cook_book Icb on I.item_id = Icb.component_id
        where Icb.component_id is null
          and name is not null
    )

    --- Most popular items w/o boots
    insert into @result_item_table
    select *
    from Item
    where name in (
        select top 5 name
        from Match_participant
                 inner join participant_items
                            on Match_participant.Summoner_summoner_id = participant_items.summoner_id
                                and Match_participant.Match_match_id = participant_items.match_id
                 inner join Item I on participant_items.item_id = I.item_id
        where Match_participant.champion_name = @championName
          and I.total_cost >= 900
          and I.name not like '%boots%'
          and I.name not like '%shoes%'
          and name in (
            select name
            from @full_items
        )
        group by name
        order by count(*) desc
    )

    -- Most popular boots
    insert into @result_item_table
    select *
    from Item
    where name in (
        select top 1 name
        from Match_participant
                 inner join participant_items
                            on Match_participant.Summoner_summoner_id = participant_items.summoner_id
                                and Match_participant.Match_match_id = participant_items.match_id
                 inner join Item I on participant_items.item_id = I.item_id
        where Match_participant.champion_name = @championName and I.total_cost >= 900 and I.name like '%boots%'
           or I.name like '%shoes%'
            and name in (
                select name
                from @full_items
            )
        group by name
        order by count(*) desc
    )

    select * from @result_item_table
end
GO
/****** Object:  StoredProcedure [dbo].[updateChampionPerks]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER procedure [dbo].[updateChampionPerks]
as
begin
    delete from champion_perk
    declare @champName nvarchar(max), @mostPopularTree int, @championId int
    declare championNamesCursor cursor for select name from Champion group by name
    open championNamesCursor
    fetch next from championNamesCursor into @champName
    while @@fetch_status = 0
        begin
            begin transaction
                exec @mostPopularTree = mostPopularPerTreeForChampion @champName = @champName
                SELECT @championId = champion_id from Champion where name = @champName
                declare @mainTreePerkId int
                declare mainTreeCursor cursor for select id from perkMainTree(@champName, @mostPopularTree)
                open mainTreeCursor
                fetch next from mainTreeCursor into @mainTreePerkId
                while @@fetch_status = 0
                    begin
                        insert into champion_perk(champion_id, perk_id, type)
                        values (@championId, @mainTreePerkId, 'MAIN')
                        fetch next from mainTreeCursor into @mainTreePerkId
                    end
                close mainTreeCursor
                deallocate mainTreeCursor

                declare @subTreePerkId int
                declare subTreeCursor cursor for select id from perkSubTree(@champName, @mostPopularTree)
                open subTreeCursor
                fetch next from subTreeCursor into @subTreePerkId
                while @@fetch_status = 0
                    begin
                        insert into champion_perk(champion_id, perk_id, type)
                        values (@championId, @subTreePerkId, 'SUB')
                        fetch next from subTreeCursor into @subTreePerkId
                    end
                close subTreeCursor
                deallocate subTreeCursor
                fetch next from championNamesCursor into @champName
            commit
        end
    close championNamesCursor
    deallocate championNamesCursor
end
GO
/****** Object:  StoredProcedure [dbo].[updateChampionStats]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER procedure [dbo].[updateChampionStats](
    @champName varchar(100)
)
as
begin
    declare @match_id varchar(30), @team_id int
    declare @temptable table
                       (
                           enemy_champion varchar(50),
                           icon_url       varchar(255),
                           match_id       varchar(255),
                           isWin          bit
                       )

    declare cur cursor for
        select m.match_id, Team_team_id
        from Match_participant
                 inner join Match M on Match_participant.Match_match_id = M.match_id
        where champion_name = @champName
    open cur

    delete from Champion_Stats Where champion_name = @champName
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
            where M2.match_id = @match_id
              and Team_team_id <> @team_id
            fetch next from cur into @match_id, @team_id
        end
    close cur
    deallocate cur


    DECLARE @Table1 TABLE
                    (
                        enemy_champion varchar(50),
                        victories      double precision
                    )
    insert into @Table1
    select enemy_champion, count(*) 'victories'
    from @temptable
    where isWin = 1
    group by enemy_champion

    DECLARE @Table2 TABLE
                    (
                        enemy_champion varchar(50),
                        defeats        double precision
                    )
    insert into @Table2
    select enemy_champion, count(*) 'defeats'
    from @temptable
    where isWin = 0
    group by enemy_champion

    DECLARE @ResultTable TABLE
                         (
                             enemy_champion varchar(50),
                             victories      double precision,
                             defeats        double precision
                         )
    insert into @ResultTable
    SELECT ISNULL(T1.enemy_champion, T2.enemy_champion) AS enemy_champion,
           ISNULL(T1.victories, 0)                      AS Value1,
           ISNULL(T2.defeats, 0)                        AS Value2
    FROM @Table1 T1
             FULL OUTER JOIN @Table2 T2 ON T2.enemy_champion = T1.enemy_champion

    insert into Champion_Stats(enemy_champion, win_ratio, games, champion_name)
    select enemy_champion, (victories / (victories + defeats)) 'win_ratio', (victories + defeats) 'games', @champName
    from @ResultTable
    where (victories + defeats > 3)
end
GO
/****** Object:  StoredProcedure [dbo].[updateMatchParticipantAveragePerformanceAggregates]    Script Date: 15.09.2022 23:25:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[updateMatchParticipantAveragePerformanceAggregates]
AS
BEGIN
    delete from match_participant_average_performance
    insert into match_participant_average_performance (avg_total_minions_killed, avg_dealt_damage_to_champions,
                                                       avg_pentakill, avg_received_damage, avg_stolen_obj,
                                                       avg_kill_participation, stdev_of_kill_participation,
                                                       avg_vision_score, stdev_of_vision_score, individual_position,
                                                       stdev_of_total_minions_killed,
                                                       stdev_of_dealt_damage_to_champions, stdev_of_pentakills,
                                                       stdev_of_received_damage, stdev_of_stolen_obj, tier)
    select AVG(ISNULL(total_minions_killed, 0)),
           AVG(ISNULL(magic_damage_dealt_to_champions, 0)),
           AVG(ISNULL(pentakills, 0)),
           AVG(ISNULL(total_damage_taken, 0)),
           AVG(ISNULL(objectives_stolen, 0)),
           AVG(ISNULL(kill_participation, 0)),
           STDEV(ISNULL(kill_participation, 0)),
           AVG(ISNULL(vision_score, 0)),
           STDEV(ISNULL(vision_score, 0)),
           individual_position,
           STDEV(ISNULL(total_minions_killed, 0)),
           STDEV(ISNULL(total_damage_dealt_to_champions, 0)),
           STDEV(ISNULL(pentakills, 0)),
           STDEV(ISNULL(total_damage_taken, 0)),
           STDEV(ISNULL(objectives_stolen, 0)),
           tier
    from Match_participant
             inner join Summoner S on Match_participant.Summoner_summoner_id = S.summoner_id
    WHERE Tier IS NOT null
    group by individual_position, tier
END
GO
