package leagueoflegendsproject.v2.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChampionInfo {

    @Id
    private Long id;

    private Integer attack;
    private Integer defense;
    private Integer magic;
    private Integer difficulty;

    @OneToOne(mappedBy = "info")
    @JoinColumn
    private Champion champion;
}
