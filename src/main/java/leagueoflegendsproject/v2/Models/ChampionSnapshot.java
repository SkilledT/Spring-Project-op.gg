package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "CMP_SNAP_UQ_VER_NAME", columnNames = { "name", "version" })
})
public class ChampionSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;
    private String key;
    private String version;
    private String name;
    private String title;
    @Column(length = 10485760)
    private String blurb;
    private String partype;
    private Instant createdAt = Instant.now();
    private Instant lastModifiedAt = Instant.now();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> tags = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChampionInfo info;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChampionImage image;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChampionStats stats;

    @OneToMany(mappedBy = "championSnapshot", fetch = FetchType.LAZY)
    private Set<MatchParticipant> matchParticipants = new HashSet<>();
    @OneToMany(mappedBy = "championSnapshot", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Ban> bans = new HashSet<>();
}
