--liquibase formatted sql
--changeset DamianWieczorek:1

/****** Object:  Table [dbo].[application_user]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'application_user'
                and xtype = 'U')
CREATE TABLE [dbo].[application_user]
(
    [username]                [varchar](255) NOT NULL,
    [isaccountnonexpired]     [int]          NULL,
    [isaccountnonlocked]      [int]          NULL,
    [iscredentialsnonexpired] [int]          NULL,
    [isenabled]               [int]          NULL,
    [password]                [varchar](255) NULL,
    PRIMARY KEY CLUSTERED
        (
         [username] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ban]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'ban'
                and xtype = 'U')
CREATE TABLE [dbo].[ban]
(
    [champion_id] [int]          NOT NULL,
    [pick_turn]   [int]          NULL,
    [match_id]    [varchar](255) NOT NULL,
    [team_id]     [int]          NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [champion_id] ASC,
         [match_id] ASC,
         [team_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[champion]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'champion'
                and xtype = 'U')
CREATE TABLE [dbo].[champion]
(
    [champion_id] [int]          NOT NULL,
    [blurb]       [varchar](max) NULL,
    [icon_url]    [varchar](max) NULL,
    [name]        [varchar](max) NULL,
    [title]       [varchar](max) NULL,
    [image_id]    [int]          NULL,
    [info_id]     [int]          NULL,
    [stats_id]    [int]          NULL,
    PRIMARY KEY CLUSTERED
        (
         [champion_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[champion_perk]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'champion_perk'
                and xtype = 'U')
CREATE TABLE [dbo].[champion_perk]
(
    [champion_id] [int]          NOT NULL,
    [perk_id]     [int]          NOT NULL,
    [type]        [varchar](max) NULL,
    PRIMARY KEY CLUSTERED
        (
         [champion_id] ASC,
         [perk_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[champion_stats]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'champion_stats'
                and xtype = 'U')
CREATE TABLE [dbo].[champion_stats]
(
    [champion_name]  [varchar](255) NOT NULL,
    [enemy_champion] [varchar](255) NOT NULL,
    [games]          [int]          NULL,
    [win_ratio]      [float]        NULL,
    PRIMARY KEY CLUSTERED
        (
         [champion_name] ASC,
         [enemy_champion] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[champion_tag]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'champion_tag'
                and xtype = 'U')
CREATE TABLE [dbo].[champion_tag]
(
    [champion_id] [int] NOT NULL,
    [tag_id]      [int] NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [champion_id] ASC,
         [tag_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[champion_with_win_ratio_entity]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'champion_with_win_ratio_entity'
                and xtype = 'U')
CREATE TABLE [dbo].[champion_with_win_ratio_entity]
(
    [name]      [varchar](255) NOT NULL,
    [games]     [int]          NULL,
    [icon_url]  [varchar](255) NULL,
    [win_ratio] [float]        NULL,
    PRIMARY KEY CLUSTERED
        (
         [name] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[image]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'image'
                and xtype = 'U')
CREATE TABLE [dbo].[image]
(
    [id]     [int] IDENTITY (1,1) NOT NULL,
    [full]   [varchar](max)       NULL,
    [group]  [varchar](max)       NULL,
    [h]      [int]                NULL,
    [sprite] [varchar](max)       NULL,
    [w]      [int]                NULL,
    [x]      [int]                NULL,
    [y]      [int]                NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[info]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'info'
                and xtype = 'U')
CREATE TABLE [dbo].[info]
(
    [id]         [int] IDENTITY (1,1) NOT NULL,
    [attack]     [int]                NULL,
    [defense]    [int]                NULL,
    [difficulty] [int]                NULL,
    [magic]      [int]                NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[item]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'item'
                and xtype = 'U')
CREATE TABLE [dbo].[item]
(
    [item_id]     [int]          NOT NULL,
    [base_cost]   [int]          NULL,
    [description] [varchar](max) NULL,
    [icon_url]    [varchar](max) NULL,
    [name]        [varchar](max) NULL,
    [plain_text]  [varchar](max) NULL,
    [sell]        [int]          NULL,
    [total_cost]  [int]          NULL,
    PRIMARY KEY CLUSTERED
        (
         [item_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[item_cook_book]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'item_cook_book'
                and xtype = 'U')
CREATE TABLE [dbo].[item_cook_book]
(
    [id]           [int] IDENTITY (1,1) NOT NULL,
    [component_id] [int]                NULL,
    [item_id]      [int]                NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[match]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'match'
                and xtype = 'U')
CREATE TABLE [dbo].[match]
(
    [match_id]             [varchar](255) NOT NULL,
    [game_creation]        [bigint]       NULL,
    [game_duration]        [int]          NULL,
    [game_id]              [int]          NULL,
    [game_mode]            [varchar](255) NULL,
    [game_start_timestamp] [int]          NULL,
    [game_type]            [varchar](255) NULL,
    [map_id]               [int]          NULL,
    [platform_id]          [varchar](255) NULL,
    [queue_id]             [int]          NULL,
    PRIMARY KEY CLUSTERED
        (
         [match_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[match_participant]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'match_participant'
                and xtype = 'U')
CREATE TABLE [dbo].[match_participant]
(
    [match_match_id]                     [varchar](255) NOT NULL,
    [summoner_summoner_id]               [varchar](255) NOT NULL,
    [inhibitor_kills]                    [int]          NULL,
    [kills]                              [int]          NULL,
    [assists]                            [int]          NULL,
    [baron_kills]                        [int]          NULL,
    [bounty_level]                       [int]          NULL,
    [champ_experience]                   [int]          NULL,
    [champ_lvl]                          [int]          NULL,
    [champion_name]                      [varchar](255) NULL,
    [champion_transform]                 [int]          NULL,
    [consumables_purchased]              [int]          NULL,
    [damage_dealt_to_objectives]         [int]          NULL,
    [damage_dealts_to_buildings]         [int]          NULL,
    [damage_dealts_to_turrets]           [int]          NULL,
    [damage_self_mitigated]              [int]          NULL,
    [deaths]                             [int]          NULL,
    [detector_wards_placed]              [int]          NULL,
    [double_kills]                       [int]          NULL,
    [dragon_kills]                       [int]          NULL,
    [first_blood_assist]                 [int]          NULL,
    [first_blood_kill]                   [int]          NULL,
    [first_tower_assist]                 [int]          NULL,
    [first_tower_kill]                   [int]          NULL,
    [game_ended_in_early_surrender]      [int]          NULL,
    [game_ended_in_surrender]            [int]          NULL,
    [gold_earned]                        [int]          NULL,
    [gold_spent]                         [int]          NULL,
    [individual_position]                [varchar](255) NULL,
    [inhibitor_takedowns]                [int]          NULL,
    [inhibitors_lost]                    [int]          NULL,
    [items_purchased]                    [int]          NULL,
    [lane]                               [varchar](255) NULL,
    [largest_critical_strike]            [int]          NULL,
    [largest_killing_spree]              [int]          NULL,
    [largest_multi_kill]                 [int]          NULL,
    [longest_time_spent_living]          [int]          NULL,
    [magic_damage_dealt]                 [int]          NULL,
    [magic_damage_dealt_to_champions]    [int]          NULL,
    [magic_damage_taken]                 [int]          NULL,
    [neutral_minions_killed]             [int]          NULL,
    [nexus_kills]                        [int]          NULL,
    [nexus_lost]                         [int]          NULL,
    [nexus_takedowns]                    [int]          NULL,
    [objectives_stolen]                  [int]          NULL,
    [objectives_stolen_assists]          [int]          NULL,
    [participant_id]                     [int]          NULL,
    [pentakills]                         [int]          NULL,
    [physical_damage_dealt]              [int]          NULL,
    [physical_damage_dealt_to_champions] [int]          NULL,
    [physical_damage_taken]              [int]          NULL,
    [quadrakills]                        [int]          NULL,
    [role]                               [varchar](255) NULL,
    [sight_wards_bought_in_game]         [int]          NULL,
    [spell1_casts]                       [int]          NULL,
    [spell2_cast]                        [int]          NULL,
    [spell3_casts]                       [int]          NULL,
    [spell4_casts]                       [int]          NULL,
    [summoner1_casts]                    [int]          NULL,
    [summoner1_id]                       [int]          NULL,
    [summoner2_casts]                    [int]          NULL,
    [summoner2_id]                       [int]          NULL,
    [team_early_surrender]               [int]          NULL,
    [team_position]                      [varchar](255) NULL,
    [time_ccing_others]                  [int]          NULL,
    [time_played]                        [int]          NULL,
    [total_damage_dealt]                 [int]          NULL,
    [total_damage_dealt_to_champions]    [int]          NULL,
    [total_damage_shielded_on_teammates] [int]          NULL,
    [total_damage_taken]                 [int]          NULL,
    [total_heal]                         [int]          NULL,
    [total_heals_on_teammates]           [int]          NULL,
    [total_minions_killed]               [int]          NULL,
    [total_time_cc_dealt]                [int]          NULL,
    [total_time_spent_dead]              [int]          NULL,
    [total_units_healed]                 [int]          NULL,
    [triple_kills]                       [int]          NULL,
    [true_damage_dealt]                  [int]          NULL,
    [true_damage_dealt_to_champions]     [int]          NULL,
    [true_damage_taken]                  [int]          NULL,
    [turret_kills]                       [int]          NULL,
    [turret_lost]                        [int]          NULL,
    [turret_takedowns]                   [int]          NULL,
    [unreal_kills]                       [int]          NULL,
    [vision_score]                       [int]          NULL,
    [vision_wards_bought_in_game]        [int]          NULL,
    [wards_killed]                       [int]          NULL,
    [wards_placed]                       [int]          NULL,
    [win]                                [int]          NULL,
    [champion_id]                        [int]          NOT NULL,
    [team_team_id]                       [int]          NOT NULL,
    [kill_participation]                 [float]        NULL,
    PRIMARY KEY CLUSTERED
        (
         [match_match_id] ASC,
         [summoner_summoner_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[match_participant_average_performance]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'match_participant_average_performance'
                and xtype = 'U')
CREATE TABLE [dbo].[match_participant_average_performance]
(
    [id]                                 [bigint] IDENTITY (1,1) NOT NULL,
    [individual_position]                [varchar](255)          NULL,
    [tier]                               [varchar](255)          NULL,
    [avg_kill_participation]             [decimal](18, 2)        NULL,
    [avg_vision_score]                   [decimal](18, 2)        NULL,
    [avg_stolen_obj]                     [decimal](18, 2)        NULL,
    [avg_dealt_damage_to_champions]      [decimal](18, 2)        NULL,
    [avg_received_damage]                [decimal](18, 2)        NULL,
    [avg_pentakill]                      [decimal](18, 2)        NULL,
    [avg_total_minions_killed]           [decimal](18, 2)        NULL,
    [stdev_of_kill_participation]        [decimal](18, 2)        NULL,
    [stdev_of_vision_score]              [decimal](18, 2)        NULL,
    [stdev_of_stolen_obj]                [decimal](18, 2)        NULL,
    [stdev_of_dealt_damage_to_champions] [decimal](18, 2)        NULL,
    [stdev_of_received_damage]           [decimal](18, 2)        NULL,
    [stdev_of_pentakills]                [decimal](18, 2)        NULL,
    [stdev_of_total_minions_killed]      [decimal](18, 2)        NULL,
    CONSTRAINT [pk_matchparticipantaverageperformance] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[match_participant_perk]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'match_participant_perk'
                and xtype = 'U')
CREATE TABLE [dbo].[match_participant_perk]
(
    [perk_id]                       [int]          NOT NULL,
    [participant_match_match_id]    [varchar](255) NOT NULL,
    [participant_match_summoner_id] [varchar](255) NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [participant_match_match_id] ASC,
         [participant_match_summoner_id] ASC,
         [perk_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[match_team]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'match_team'
                and xtype = 'U')
CREATE TABLE [dbo].[match_team]
(
    [match_id] [varchar](255) NOT NULL,
    [team_id]  [int]          NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [match_id] ASC,
         [team_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[objective]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'objective'
                and xtype = 'U')
CREATE TABLE [dbo].[objective]
(
    [name] [varchar](255) NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [name] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[participant_items]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'participant_items'
                and xtype = 'U')
CREATE TABLE [dbo].[participant_items]
(
    [item_id]     [int]          NOT NULL,
    [match_id]    [varchar](255) NOT NULL,
    [summoner_id] [varchar](255) NOT NULL,
    [position]    [int]          NOT NULL,
    CONSTRAINT [PK__ParticipantItem] PRIMARY KEY CLUSTERED
        (
         [item_id] ASC,
         [match_id] ASC,
         [summoner_id] ASC,
         [position] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[party_reservation]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'party_reservation'
                and xtype = 'U')
CREATE TABLE [dbo].[party_reservation]
(
    [id]                 [bigint]       NOT NULL,
    [end_date]           [datetime2](7) NULL,
    [participant_number] [int]          NOT NULL,
    [start_date]         [datetime2](7) NULL,
    [status]             [varchar](255) NULL,
    [customer_id]        [bigint]       NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[party_reservation_reserved_attractions]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'party_reservation_reserved_attractions'
                and xtype = 'U')
CREATE TABLE [dbo].[party_reservation_reserved_attractions]
(
    [party_reservation_id]    [bigint] NOT NULL,
    [reserved_attractions_id] [bigint] NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [party_reservation_id] ASC,
         [reserved_attractions_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[perk]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'perk'
                and xtype = 'U')
CREATE TABLE [dbo].[perk]
(
    [id]          [int]          NOT NULL,
    [icon]        [varchar](255) NULL,
    [long_desc]   [varchar](max) NULL,
    [name]        [varchar](max) NULL,
    [short_desc]  [varchar](max) NULL,
    [slot_number] [int]          NULL,
    [tree_number] [int]          NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[stats]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'stats'
                and xtype = 'U')
CREATE TABLE [dbo].[stats]
(
    [id]                   [int] IDENTITY (1,1) NOT NULL,
    [armor]                [float]              NULL,
    [armorperlevel]        [float]              NULL,
    [attackdamage]         [float]              NULL,
    [attackdamageperlevel] [float]              NULL,
    [attackrange]          [float]              NULL,
    [attackspeed]          [float]              NULL,
    [attackspeedperlevel]  [float]              NULL,
    [crit]                 [float]              NULL,
    [critperlevel]         [float]              NULL,
    [hp]                   [float]              NULL,
    [hpperlevel]           [float]              NULL,
    [hpregen]              [float]              NULL,
    [hpregenperlevel]      [float]              NULL,
    [movespeed]            [float]              NULL,
    [mp]                   [float]              NULL,
    [mpperlevel]           [float]              NULL,
    [mpregen]              [float]              NULL,
    [mpregenperlevel]      [float]              NULL,
    [spellblock]           [float]              NULL,
    [spellblockperlevel]   [float]              NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[summoner]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'summoner'
                and xtype = 'U')
CREATE TABLE [dbo].[summoner]
(
    [summoner_id]       [varchar](255) NOT NULL,
    [league_points]     [int]          NULL,
    [losses]            [int]          NULL,
    [lvl]               [int]          NULL,
    [profile_icon_id]   [int]          NULL,
    [rank]              [varchar](255) NULL,
    [summoner_nickname] [varchar](255) NULL,
    [tier]              [varchar](255) NULL,
    [wins]              [int]          NULL,
    PRIMARY KEY CLUSTERED
        (
         [summoner_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tag]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'tag'
                and xtype = 'U')
CREATE TABLE [dbo].[tag]
(
    [id]   [int] IDENTITY (1,1) NOT NULL,
    [name] [varchar](255)       NULL,
    PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[team]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'team'
                and xtype = 'U')
CREATE TABLE [dbo].[team]
(
    [team_id] [int] NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [team_id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[team_objective]    Script Date: 15.09.2022 21:03:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS(SELECT *
              FROM sysobjects
              WHERE name = 'team_objective'
                and xtype = 'U')
CREATE TABLE [dbo].[team_objective]
(
    [objective_name] [varchar](255) NOT NULL,
    [first]          [int]          NULL,
    [kills]          [int]          NULL,
    [match_id]       [varchar](255) NOT NULL,
    [team_id]        [int]          NOT NULL,
    PRIMARY KEY CLUSTERED
        (
         [match_id] ASC,
         [team_id] ASC,
         [objective_name] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK6xshxc1cu1yvxoc9ko2hlom4l]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[ban]'))
ALTER TABLE dbo.[ban]
    DROP CONSTRAINT [FK6xshxc1cu1yvxoc9ko2hlom4l]
GO
ALTER TABLE [dbo].[ban]
    WITH CHECK ADD CONSTRAINT [FK6xshxc1cu1yvxoc9ko2hlom4l] FOREIGN KEY ([champion_id])
        REFERENCES [dbo].[champion] ([champion_id])
GO
ALTER TABLE [dbo].[ban]
    CHECK CONSTRAINT [FK6xshxc1cu1yvxoc9ko2hlom4l]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKdms8ohv2cei6huq6275j8c8ct]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[ban]'))
ALTER TABLE dbo.[ban]
    DROP CONSTRAINT [FKdms8ohv2cei6huq6275j8c8ct]
GO
ALTER TABLE [dbo].[ban]
    WITH CHECK ADD CONSTRAINT [FKdms8ohv2cei6huq6275j8c8ct] FOREIGN KEY ([match_id], [team_id])
        REFERENCES [dbo].[match_team] ([match_id], [team_id])
GO
ALTER TABLE [dbo].[ban]
    CHECK CONSTRAINT [FKdms8ohv2cei6huq6275j8c8ct]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKc6n6t38rjulhqcmo41hv5o5g8]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion]'))
ALTER TABLE dbo.[champion]
    DROP CONSTRAINT [FKc6n6t38rjulhqcmo41hv5o5g8]
GO
ALTER TABLE [dbo].[champion]
    WITH CHECK ADD CONSTRAINT [FKc6n6t38rjulhqcmo41hv5o5g8] FOREIGN KEY ([stats_id])
        REFERENCES [dbo].[stats] ([id])
GO
ALTER TABLE [dbo].[champion]
    CHECK CONSTRAINT [FKc6n6t38rjulhqcmo41hv5o5g8]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKe96ieyt47v7gxxurhoi5rh57v]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion]'))
ALTER TABLE dbo.[champion]
    DROP CONSTRAINT [FKe96ieyt47v7gxxurhoi5rh57v]
GO
ALTER TABLE [dbo].[champion]
    WITH CHECK ADD CONSTRAINT [FKe96ieyt47v7gxxurhoi5rh57v] FOREIGN KEY ([info_id])
        REFERENCES [dbo].[info] ([id])
GO
ALTER TABLE [dbo].[champion]
    CHECK CONSTRAINT [FKe96ieyt47v7gxxurhoi5rh57v]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKq8d4nmcsnmtclrq7ka23xd4h2]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion]'))
ALTER TABLE dbo.[champion]
    DROP CONSTRAINT [FKq8d4nmcsnmtclrq7ka23xd4h2]
GO
ALTER TABLE [dbo].[champion]
    WITH CHECK ADD CONSTRAINT [FKq8d4nmcsnmtclrq7ka23xd4h2] FOREIGN KEY ([image_id])
        REFERENCES [dbo].[image] ([id])
GO
ALTER TABLE [dbo].[champion]
    CHECK CONSTRAINT [FKq8d4nmcsnmtclrq7ka23xd4h2]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK8n2yl9j6amps9lvwsogp208s2]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion_perk]'))
ALTER TABLE dbo.[champion_perk]
    DROP CONSTRAINT [FK8n2yl9j6amps9lvwsogp208s2]
GO
ALTER TABLE [dbo].[champion_perk]
    WITH CHECK ADD CONSTRAINT [FK8n2yl9j6amps9lvwsogp208s2] FOREIGN KEY ([champion_id])
        REFERENCES [dbo].[champion] ([champion_id])
GO
ALTER TABLE [dbo].[champion_perk]
    CHECK CONSTRAINT [FK8n2yl9j6amps9lvwsogp208s2]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKib262qv4u6tyviwljspc984vd]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion_perk]'))
ALTER TABLE dbo.[champion_perk]
    DROP CONSTRAINT [FKib262qv4u6tyviwljspc984vd]
GO
ALTER TABLE [dbo].[champion_perk]
    WITH CHECK ADD CONSTRAINT [FKib262qv4u6tyviwljspc984vd] FOREIGN KEY ([perk_id])
        REFERENCES [dbo].[perk] ([id])
GO
ALTER TABLE [dbo].[champion_perk]
    CHECK CONSTRAINT [FKib262qv4u6tyviwljspc984vd]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKhrs43b7bsohemxab22ws3aff5]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion_tag]'))
ALTER TABLE dbo.[champion_tag]
    DROP CONSTRAINT [FKhrs43b7bsohemxab22ws3aff5]
GO
ALTER TABLE [dbo].[champion_tag]
    WITH CHECK ADD CONSTRAINT [FKhrs43b7bsohemxab22ws3aff5] FOREIGN KEY ([champion_id])
        REFERENCES [dbo].[champion] ([champion_id])
GO
ALTER TABLE [dbo].[champion_tag]
    CHECK CONSTRAINT [FKhrs43b7bsohemxab22ws3aff5]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKr3hu6bg7br0xdy71au4br8ocj]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[champion_tag]'))
ALTER TABLE dbo.[champion_tag]
    DROP CONSTRAINT [FKr3hu6bg7br0xdy71au4br8ocj]
GO
ALTER TABLE [dbo].[champion_tag]
    WITH CHECK ADD CONSTRAINT [FKr3hu6bg7br0xdy71au4br8ocj] FOREIGN KEY ([tag_id])
        REFERENCES [dbo].[tag] ([id])
GO
ALTER TABLE [dbo].[champion_tag]
    CHECK CONSTRAINT [FKr3hu6bg7br0xdy71au4br8ocj]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK7rxktnn02xhevp7py1q852ld0]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[item_cook_book]'))
ALTER TABLE dbo.[item_cook_book]
    DROP CONSTRAINT [FK7rxktnn02xhevp7py1q852ld0]
GO
ALTER TABLE [dbo].[item_cook_book]
    WITH CHECK ADD CONSTRAINT [FK7rxktnn02xhevp7py1q852ld0] FOREIGN KEY ([component_id])
        REFERENCES [dbo].[item] ([item_id])
GO
ALTER TABLE [dbo].[item_cook_book]
    CHECK CONSTRAINT [FK7rxktnn02xhevp7py1q852ld0]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKbdejvnsi2ptscefl04m3eeat5]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[item_cook_book]'))
ALTER TABLE dbo.[item_cook_book]
    DROP CONSTRAINT [FKbdejvnsi2ptscefl04m3eeat5]
GO
ALTER TABLE [dbo].[item_cook_book]
    WITH CHECK ADD CONSTRAINT [FKbdejvnsi2ptscefl04m3eeat5] FOREIGN KEY ([item_id])
        REFERENCES [dbo].[item] ([item_id])
GO
ALTER TABLE [dbo].[item_cook_book]
    CHECK CONSTRAINT [FKbdejvnsi2ptscefl04m3eeat5]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKfxwsems2skk43wgophjrwboaf]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant]'))
ALTER TABLE dbo.[match_participant]
    DROP CONSTRAINT [FKfxwsems2skk43wgophjrwboaf]
GO
ALTER TABLE [dbo].[match_participant]
    WITH CHECK ADD CONSTRAINT [FKfxwsems2skk43wgophjrwboaf] FOREIGN KEY ([team_team_id])
        REFERENCES [dbo].[team] ([team_id])
GO
ALTER TABLE [dbo].[match_participant]
    CHECK CONSTRAINT [FKfxwsems2skk43wgophjrwboaf]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKgupw0mja9f4cyedvjoodopfn1]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant]'))
ALTER TABLE dbo.[match_participant]
    DROP CONSTRAINT [FKgupw0mja9f4cyedvjoodopfn1]
GO
ALTER TABLE [dbo].[match_participant]
    WITH CHECK ADD CONSTRAINT [FKgupw0mja9f4cyedvjoodopfn1] FOREIGN KEY ([match_match_id])
        REFERENCES [dbo].[match] ([match_id])
GO
ALTER TABLE [dbo].[match_participant]
    CHECK CONSTRAINT [FKgupw0mja9f4cyedvjoodopfn1]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKhcvbrphsvbs4mlcprmduexg2t]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant]'))
ALTER TABLE dbo.[match_participant]
    DROP CONSTRAINT [FKhcvbrphsvbs4mlcprmduexg2t]
GO
ALTER TABLE [dbo].[match_participant]
    WITH CHECK ADD CONSTRAINT [FKhcvbrphsvbs4mlcprmduexg2t] FOREIGN KEY ([summoner_summoner_id])
        REFERENCES [dbo].[summoner] ([summoner_id])
GO
ALTER TABLE [dbo].[match_participant]
    CHECK CONSTRAINT [FKhcvbrphsvbs4mlcprmduexg2t]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKmi08deu6kp3o0lutovlnqiyn9]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant]'))
ALTER TABLE dbo.[match_participant]
    DROP CONSTRAINT [FKmi08deu6kp3o0lutovlnqiyn9]
GO
ALTER TABLE [dbo].[match_participant]
    WITH CHECK ADD CONSTRAINT [FKmi08deu6kp3o0lutovlnqiyn9] FOREIGN KEY ([champion_id])
        REFERENCES [dbo].[champion] ([champion_id])
GO
ALTER TABLE [dbo].[match_participant]
    CHECK CONSTRAINT [FKmi08deu6kp3o0lutovlnqiyn9]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKdf1mr4b1dq2lbm02qiut8qwhm]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant_perk]'))
ALTER TABLE dbo.[match_participant_perk]
    DROP CONSTRAINT [FKdf1mr4b1dq2lbm02qiut8qwhm]
GO
ALTER TABLE [dbo].[match_participant_perk]
    WITH CHECK ADD CONSTRAINT [FKdf1mr4b1dq2lbm02qiut8qwhm] FOREIGN KEY ([perk_id])
        REFERENCES [dbo].[perk] ([id])
GO
ALTER TABLE [dbo].[match_participant_perk]
    CHECK CONSTRAINT [FKdf1mr4b1dq2lbm02qiut8qwhm]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKep8aukqvl535pic08vdu0fso4]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_participant_perk]'))
ALTER TABLE dbo.[match_participant_perk]
    DROP CONSTRAINT [FKep8aukqvl535pic08vdu0fso4]
GO
ALTER TABLE [dbo].[match_participant_perk]
    WITH CHECK ADD CONSTRAINT [FKep8aukqvl535pic08vdu0fso4] FOREIGN KEY ([participant_match_match_id], [participant_match_summoner_id])
        REFERENCES [dbo].[match_participant] ([match_match_id], [summoner_summoner_id])
GO
ALTER TABLE [dbo].[match_participant_perk]
    CHECK CONSTRAINT [FKep8aukqvl535pic08vdu0fso4]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK48uy7lsuj12rwhg2c6wo353s0]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_team]'))
ALTER TABLE dbo.[match_team]
    DROP CONSTRAINT [FK48uy7lsuj12rwhg2c6wo353s0]
GO
ALTER TABLE [dbo].[match_team]
    WITH CHECK ADD CONSTRAINT [FK48uy7lsuj12rwhg2c6wo353s0] FOREIGN KEY ([match_id])
        REFERENCES [dbo].[match] ([match_id])
GO
ALTER TABLE [dbo].[match_team]
    CHECK CONSTRAINT [FK48uy7lsuj12rwhg2c6wo353s0]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKxo5n3gccka8uuahnuh0xo9w7]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[match_team]'))
ALTER TABLE dbo.[match_team]
    DROP CONSTRAINT [FKxo5n3gccka8uuahnuh0xo9w7]
GO
ALTER TABLE [dbo].[match_team]
    WITH CHECK ADD CONSTRAINT [FKxo5n3gccka8uuahnuh0xo9w7] FOREIGN KEY ([team_id])
        REFERENCES [dbo].[team] ([team_id])
GO
ALTER TABLE [dbo].[match_team]
    CHECK CONSTRAINT [FKxo5n3gccka8uuahnuh0xo9w7]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKasdul5ertcly9urxoxri2ro0m]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[participant_items]'))
ALTER TABLE dbo.[participant_items]
    DROP CONSTRAINT [FKasdul5ertcly9urxoxri2ro0m]
GO
ALTER TABLE [dbo].[participant_items]
    WITH CHECK ADD CONSTRAINT [FKasdul5ertcly9urxoxri2ro0m] FOREIGN KEY ([item_id])
        REFERENCES [dbo].[item] ([item_id])
GO
ALTER TABLE [dbo].[participant_items]
    CHECK CONSTRAINT [FKasdul5ertcly9urxoxri2ro0m]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKodojas1kix997w8cywc97s6ga]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[participant_items]'))
ALTER TABLE dbo.[participant_items]
    DROP CONSTRAINT [FKodojas1kix997w8cywc97s6ga]
GO
ALTER TABLE [dbo].[participant_items]
    WITH CHECK ADD CONSTRAINT [FKodojas1kix997w8cywc97s6ga] FOREIGN KEY ([match_id], [summoner_id])
        REFERENCES [dbo].[match_participant] ([match_match_id], [summoner_summoner_id])
GO
ALTER TABLE [dbo].[participant_items]
    CHECK CONSTRAINT [FKodojas1kix997w8cywc97s6ga]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKaxn1769rh4ns38ch7m08m6gf0]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[party_reservation]'))
ALTER TABLE dbo.[party_reservation]
    DROP CONSTRAINT [FKaxn1769rh4ns38ch7m08m6gf0]
GO
ALTER TABLE [dbo].[party_reservation]
    WITH CHECK ADD CONSTRAINT [FKaxn1769rh4ns38ch7m08m6gf0] FOREIGN KEY ([customer_id])
        REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[party_reservation]
    CHECK CONSTRAINT [FKaxn1769rh4ns38ch7m08m6gf0]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK5efs4hvi6wfjw58l6y7efgqu3]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[party_reservation_reserved_attractions]'))
ALTER TABLE dbo.[party_reservation_reserved_attractions]
    DROP CONSTRAINT [FK5efs4hvi6wfjw58l6y7efgqu3]
GO
ALTER TABLE [dbo].[party_reservation_reserved_attractions]
    WITH CHECK ADD CONSTRAINT [FK5efs4hvi6wfjw58l6y7efgqu3] FOREIGN KEY ([reserved_attractions_id])
        REFERENCES [dbo].[attraction] ([id])
GO
ALTER TABLE [dbo].[party_reservation_reserved_attractions]
    CHECK CONSTRAINT [FK5efs4hvi6wfjw58l6y7efgqu3]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FKg2wvahv13m31ft56rs9l5sgev]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[party_reservation_reserved_attractions]'))
ALTER TABLE dbo.[party_reservation_reserved_attractions]
    DROP CONSTRAINT [FKg2wvahv13m31ft56rs9l5sgev]
GO
ALTER TABLE [dbo].[party_reservation_reserved_attractions]
    WITH CHECK ADD CONSTRAINT [FKg2wvahv13m31ft56rs9l5sgev] FOREIGN KEY ([party_reservation_id])
        REFERENCES [dbo].[party_reservation] ([id])
GO
ALTER TABLE [dbo].[party_reservation_reserved_attractions]
    CHECK CONSTRAINT [FKg2wvahv13m31ft56rs9l5sgev]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK19emi74fv8f5o1thl9a1jyocs]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[team_objective]'))
ALTER TABLE dbo.[team_objective]
    DROP CONSTRAINT [FK19emi74fv8f5o1thl9a1jyocs]
GO
ALTER TABLE [dbo].[team_objective]
    WITH CHECK ADD CONSTRAINT [FK19emi74fv8f5o1thl9a1jyocs] FOREIGN KEY ([match_id], [team_id])
        REFERENCES [dbo].[match_team] ([match_id], [team_id])
GO
ALTER TABLE [dbo].[team_objective]
    CHECK CONSTRAINT [FK19emi74fv8f5o1thl9a1jyocs]
GO
IF EXISTS(SELECT *
          FROM sys.foreign_keys
          WHERE object_id = OBJECT_ID(N'[dbo].[FK7g5shlg3so99vdn9re99w1kvd]')
            AND parent_object_id = OBJECT_ID(N'[dbo].[team_objective]'))
ALTER TABLE dbo.[team_objective]
    DROP CONSTRAINT [FK7g5shlg3so99vdn9re99w1kvd]
GO
ALTER TABLE [dbo].[team_objective]
    WITH CHECK ADD CONSTRAINT [FK7g5shlg3so99vdn9re99w1kvd] FOREIGN KEY ([objective_name])
        REFERENCES [dbo].[objective] ([name])
GO
ALTER TABLE [dbo].[team_objective]
    CHECK CONSTRAINT [FK7g5shlg3so99vdn9re99w1kvd]
GO