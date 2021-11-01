package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanKey implements Serializable {

    @Embedded
    private MatchTeamKey matchTeamKey;

    private Long championId;
}
