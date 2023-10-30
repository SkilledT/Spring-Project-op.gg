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
public class MatchParticipantItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MatchParticipant matchParticipant;
}
