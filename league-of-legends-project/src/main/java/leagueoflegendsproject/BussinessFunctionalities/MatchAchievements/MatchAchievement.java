package leagueoflegendsproject.BussinessFunctionalities.MatchAchievements;

import leagueoflegendsproject.DTOs.AchievementType;
import leagueoflegendsproject.DTOs.MatchAchievementDTO;
import leagueoflegendsproject.DTOs.MatchDetailsDto;
import leagueoflegendsproject.Models.Database.MatchParticipant;

public class MatchAchievement {

    public static MatchDetailsDto getSummonerAchievementsInMatch(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        checkForNoDamageToTurrets(matchParticipant, matchDetailsDto);
        checkForNoWardsPlacedAchievement(matchParticipant, matchDetailsDto);
        checkForAntiKDAPlayer(matchParticipant, matchDetailsDto);
        checkForFogOfWarBringer(matchParticipant, matchDetailsDto);
        checkForImmortal(matchParticipant, matchDetailsDto);
        return matchDetailsDto;
    }

    private static void checkForNoWardsPlacedAchievement(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        String name = "No wards placed";
        String description = "You have NOT placed any controlling ward in this game";
        if (matchParticipant.getSightWardsBoughtInGame() == 0)
            matchDetailsDto.getMatchAchievementDTOS().add(
                    new MatchAchievementDTO(name, description, AchievementType.NEGATIVE)
            );
    }

    private static void checkForNoDamageToTurrets(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        String name = "No damage dealt to turrets";
        String description = "You did NOT damage to turrets. Gold from turrets provides significant amount of gold. Work on it";
        if (matchParticipant.getDamageDealtsToTurrets() == 0)
            matchDetailsDto.getMatchAchievementDTOS().add(
                    new MatchAchievementDTO(name, description, AchievementType.NEGATIVE)
            );
    }

    private static void checkForAntiKDAPlayer(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        String name = "Anti-KDA Player";
        String description = "Your KDA in this game was below 1. It means you died more times than were involved it kills. It does not looks good";
        if ((matchParticipant.getKills() + matchParticipant.getAssists()) < matchParticipant.getDeaths())
            matchDetailsDto.getMatchAchievementDTOS().add(
                    new MatchAchievementDTO(name, description, AchievementType.NEGATIVE)
            );
    }

    private static void checkForFogOfWarBringer(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        String name = "Fog of War Bringer";
        String description = "You've got to be a Fog of War Bringer. Good job with clearing " + matchParticipant.getWardsKilled();
        if (matchParticipant.getWardsKilled() > 5)
            matchDetailsDto.getMatchAchievementDTOS().add(
                    new MatchAchievementDTO(name, description, AchievementType.POSITIVE)
            );
    }

    private static void checkForImmortal(MatchParticipant matchParticipant, MatchDetailsDto matchDetailsDto) {
        String name = "Immortal";
        String description = "Dying throws the games. Well done with no deaths in this match. Keep doing.";
        if (matchParticipant.getDeaths() == 0)
            matchDetailsDto.getMatchAchievementDTOS().add(
                    new MatchAchievementDTO(name, description, AchievementType.POSITIVE)
            );
    }
}
