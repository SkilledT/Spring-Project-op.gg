package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "PATCH_VERSION_UQ_VERSION", columnNames = { "version" })
})
public class PatchVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    private Timestamp timestamp;
}
