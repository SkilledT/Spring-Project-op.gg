USE [lolproject]
GO

/****** Object:  StoredProcedure [dbo].[champion_with_win_ratio]    Script Date: 26.01.2022 01:02:04 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER procedure [dbo].[champion_with_win_ratio]
    (@championName varchar(100), @minimumMatches int = 3)
as
begin
    SET NOCOUNT ON;
    drop table if exists Champion_With_Win_Ratio_Entity
    create table Champion_With_Win_Ratio_Entity(
        name varchar(100),
        win_ratio double precision,
        icon_url varchar(255),
        games int
    )
    insert into Champion_With_Win_Ratio_Entity
    select coalesce(c.name, x.champion) as name,
           coalesce(x.win_ratio, -1) as win_ratio,
           coalesce(c.icon_url, '') as icon_url,
           coalesce(x.games, 0) as games
    from Champion c
             full outer join (select * from [dbo].championStats(@championName)) x
                             on name = champion
    where win_ratio > -1 and games > @minimumMatches

    select * from Champion_With_Win_Ratio_Entity;
end
GO

