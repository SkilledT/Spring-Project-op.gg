package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions;

import java.util.Objects;

public class Info {
    private Integer attack;
    private Integer defense;
    private Integer magic;
    private Integer difficulty;

    public Info() {
    }

    public Info(Integer attack, Integer defense, Integer magic, Integer difficulty) {
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(attack, info.attack) &&
                Objects.equals(defense, info.defense) &&
                Objects.equals(magic, info.magic) &&
                Objects.equals(difficulty, info.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attack, defense, magic, difficulty);
    }

    @Override
    public String toString() {
        return "Info{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", magic=" + magic +
                ", difficulty=" + difficulty +
                '}';
    }
}
