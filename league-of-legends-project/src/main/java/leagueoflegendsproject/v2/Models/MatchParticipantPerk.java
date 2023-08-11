package leagueoflegendsproject.v2.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchParticipantPerk {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Perk perk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MatchParticipant matchParticipant;
}
