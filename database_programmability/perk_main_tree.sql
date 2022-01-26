USE [lolproject]
GO

/****** Object:  UserDefinedFunction [dbo].[perkMainTree]    Script Date: 26.01.2022 01:03:35 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER function [dbo].[perkMainTree](@champion_name varchar(50),
                            @most_occurring_tree int)
    returns table
return
(
    select * from Perk where name in (
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

