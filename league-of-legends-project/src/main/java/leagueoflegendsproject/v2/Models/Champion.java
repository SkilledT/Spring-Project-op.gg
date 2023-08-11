package leagueoflegendsproject.v2.Models;

import com.google.gson.annotations.SerializedName;
import leagueoflegendsproject.Models.LoLApi.Champions.Image;
import leagueoflegendsproject.Models.LoLApi.Champions.Info;
import leagueoflegendsproject.Models.LoLApi.Champions.Stats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Champion {

    @Id
    private Long id;

    private String externalId;
    private String key;
    private String version;
    private String name;
    private String title;
    private String blurb;
    private String partype;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> tags = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ChampionInfo info;
    @OneToOne(cascade = CascadeType.ALL)
    private ChampionImage image;
    @OneToOne(cascade = CascadeType.ALL)
    private ChampionStats stats;

    @OneToMany(mappedBy = "champion", fetch = FetchType.LAZY)
    private Set<MatchParticipant> matchParticipants = new HashSet<>();

    @OneToMany(mappedBy = "champion", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Ban> bans = new HashSet<>();
}
