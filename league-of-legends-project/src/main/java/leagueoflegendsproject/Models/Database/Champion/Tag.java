package leagueoflegendsproject.Models.Database.Champion;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<ChampionTag> championTagSet = new HashSet<>();

    public Tag() {
    }

    public Tag(Integer id, String name, Set<ChampionTag> championTagSet) {
        this.id = id;
        this.name = name;
        this.championTagSet = championTagSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChampionTag> getChampionTagSet() {
        return championTagSet;
    }

    public void setChampionTagSet(Set<ChampionTag> championTagSet) {
        this.championTagSet = championTagSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
