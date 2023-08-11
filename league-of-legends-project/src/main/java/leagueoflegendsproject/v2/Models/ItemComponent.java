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
public class ItemComponent {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item masterComponent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item subComponent;
}
