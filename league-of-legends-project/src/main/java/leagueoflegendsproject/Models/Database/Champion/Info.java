package leagueoflegendsproject.Models.Database.Champion;

import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Info")
public class Info {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attack")
    private Integer attack;
    @Column(name = "defense")
    private Integer defense;
    @Column(name = "magic")
    private Integer magic;
    @Column(name = "difficulty")
    private Integer difficulty;

    @OneToMany(mappedBy = "info")
    private Set<Champion> championSet = new HashSet<>();

    public Info() {
    }

    public Info(ChampionItem championItem){
        this.attack = championItem.getInfo().getAttack();
        this.defense = championItem.getInfo().getDefense();
        this.magic = championItem.getInfo().getMagic();
        this.difficulty = championItem.getInfo().getDifficulty();
    }

    public Info(Integer id, Integer attack, Integer defense, Integer magic, Integer difficulty, Set<Champion> championSet) {
        this.id = id;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
        this.championSet = championSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Champion> getChampionSet() {
        return championSet;
    }

    public void setChampionSet(Set<Champion> championSet) {
        this.championSet = championSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(id, info.id) &&
                Objects.equals(attack, info.attack) &&
                Objects.equals(defense, info.defense) &&
                Objects.equals(magic, info.magic) &&
                Objects.equals(difficulty, info.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attack, defense, magic, difficulty);
    }
}
