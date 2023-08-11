package leagueoflegendsproject.v2.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer kills;
    private Boolean first;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Baron baron;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Inhibitor inhibitor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dragon dragon;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RiftHerald riftHerald;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ObjectiveChampion champion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tower tower;
}
