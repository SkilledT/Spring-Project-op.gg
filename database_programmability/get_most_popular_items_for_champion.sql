USE [lolproject]
GO

/****** Object:  StoredProcedure [dbo].[get_most_popular_items_for_champion]    Script Date: 26.01.2022 01:02:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER procedure [dbo].[get_most_popular_items_for_champion]
(@championName nvarchar(100))
as
    begin
        SET NOCOUNT ON;
        declare @result_item_table table
        (
            item_id int,
            icon_url nvarchar(max),
            plain_text nvarchar(max),
            total_cost int,
            sell int,
            base_cost int,
            description nvarchar(max),
            name nvarchar(max)
        )

        -- get full items table, not components
        declare @full_items table
        (
            name nvarchar(max),
            total_cost int
        )
        
        insert into  @full_items
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
            where Icb.component_id is null and name is not null
        )

        --- Most popular items w/o boots
        insert into @result_item_table
        select * from Item where name in (
            select top 5 name
            from Match_participant
                     inner join participant_items
                                on Match_participant.Summoner_summoner_id = participant_items.summoner_id
                                    and Match_participant.Match_match_id = participant_items.match_id
                     inner join Item I on participant_items.item_id = I.item_id
            where Match_participant.champion_name = @championName and I.total_cost >= 900 and I.name not like '%boots%' and I.name not like '%shoes%'
              and name in (
                select name
                from @full_items
            )
            group by name
            order by count(*) desc
        )

        -- Most popular boots
        insert into @result_item_table
        select * from Item where name in (
            select top 1 name
            from Match_participant
                     inner join participant_items
                                on Match_participant.Summoner_summoner_id = participant_items.summoner_id
                                    and Match_participant.Match_match_id = participant_items.match_id
                     inner join Item I on participant_items.item_id = I.item_id
            where Match_participant.champion_name = @championName and I.total_cost >= 900 and I.name like '%boots%' or I.name like '%shoes%'
              and name in (
                select name
                from @full_items
            )
            group by name
            order by count(*) desc
        )
        
        select * from @result_item_table
    end;
GO

