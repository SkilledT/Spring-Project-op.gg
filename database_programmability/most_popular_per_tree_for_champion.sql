USE [lolproject]
GO

/****** Object:  UserDefinedFunction [dbo].[mostPopularPerTreeForChampion]    Script Date: 26.01.2022 01:04:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER function [dbo].[mostPopularPerTreeForChampion]
(
    @champName varchar(30)
)
    returns int
as
begin
    return(
        select p.tree_number from Match_participant
            inner join Match_Participant_Perk MPP
                on Match_participant.Summoner_summoner_id = MPP.participant_match_summoner_id
                    and Match_participant.Match_match_id = MPP.participant_match_match_id
            inner join Perk P
                on MPP.perk_id = P.id
        where champion_name = @champName
        group by p.tree_number
        having count(*) >= ALL(
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

