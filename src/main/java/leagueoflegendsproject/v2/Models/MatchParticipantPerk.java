package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MatchParticipantPerk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Perk perk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MatchParticipant matchParticipant;

    @Enumerated(EnumType.STRING)
    private StyleType styleType;

    public enum StyleType {
        PRIMARY,
        SUB_STYLE,
        UNKNOWN
    }
}
