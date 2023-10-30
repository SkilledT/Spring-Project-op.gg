package leagueoflegendsproject.v2.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item masterComponent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item subComponent;
}
