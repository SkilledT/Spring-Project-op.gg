USE [lolproject]
GO

/****** Object:  UserDefinedFunction [dbo].[championWinRatio]    Script Date: 26.01.2022 01:04:14 ******/
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
    where champion_name = @champName and win = 1
    select @games = count(*)
    from Match_participant
    where champion_name = @champName
    return (@wins / @games) * 100;
end
GO

