USE [lolproject]
GO

/****** Object:  UserDefinedFunction [dbo].[perkSubTree]    Script Date: 26.01.2022 01:03:53 ******/
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

