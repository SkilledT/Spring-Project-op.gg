package leagueoflegendsproject.DTOs.MatchTable;

import leagueoflegendsproject.DTOs.PlayerGameDto;

import java.util.ArrayList;
import java.util.List;

public class MatchSummarizeDto {

    List<String> itemIds = new ArrayList<>();
    List<PlayerGameDto> allies = new ArrayList<>();
    List<PlayerGameDto> enemies = new ArrayList<>();
}
