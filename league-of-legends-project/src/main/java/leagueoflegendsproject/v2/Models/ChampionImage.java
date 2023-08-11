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
public class ChampionImage {

    @Id
    private Long id;

    private String full;
    private String sprite;
    private String group;

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;

    @OneToOne(mappedBy = "image")
    @JoinColumn
    private Champion champion;
}
