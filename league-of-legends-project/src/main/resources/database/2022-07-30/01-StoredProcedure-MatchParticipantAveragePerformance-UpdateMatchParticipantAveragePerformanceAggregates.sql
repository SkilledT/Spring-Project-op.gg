--liquibase formatted sql
--changeset DamianWieczorek:1

CREATE OR ALTER PROCEDURE updateMatchParticipantAveragePerformanceAggregates
    AS
BEGIN
    delete from match_participant_average_performance
    insert into match_participant_average_performance (avg_total_minions_killed, avg_dealt_damage_to_champions, avg_pentakill, avg_received_damage, avg_stolen_obj, avg_kill_participation, stdev_of_kill_participation, avg_vision_score, stdev_of_vision_score, individual_position, stdev_of_total_minions_killed, stdev_of_dealt_damage_to_champions, stdev_of_pentakills, stdev_of_received_damage, stdev_of_stolen_obj, tier)
    select AVG(ISNULL(total_minions_killed, 0)), AVG(ISNULL(magic_damage_dealt_to_champions, 0)), AVG(ISNULL(pentakills, 0)), AVG(ISNULL(total_damage_taken, 0)), AVG(ISNULL(objectives_stolen, 0)), AVG(ISNULL(kill_participation, 0)), STDEV(ISNULL(kill_participation, 0)), AVG(ISNULL(vision_score, 0)), STDEV(ISNULL(vision_score, 0)), individual_position,   STDEV(ISNULL(total_minions_killed, 0)), STDEV(ISNULL(total_damage_dealt_to_champions, 0)), STDEV(ISNULL(pentakills, 0)), STDEV(ISNULL(total_damage_taken, 0)), STDEV(ISNULL(objectives_stolen, 0)), tier
           from Match_participant
                    inner join Summoner S on Match_participant.Summoner_summoner_id = S.summoner_id
           WHERE Tier IS NOT null
           group by individual_position, tier
END
go;