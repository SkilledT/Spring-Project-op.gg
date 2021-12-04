package leagueoflegendsproject.Models.Database.Champion;

import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Stats")
public class Stats {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "hp")
    private Double hp;
    @Column(name = "hpperlevel")
    private Double hpperlevel;
    @Column(name = "mp")
    private Double mp;
    @Column(name = "mpperlevel")
    private Double mpperlevel;
    @Column(name = "movespeed")
    private Double movespeed;
    @Column(name = "armor")
    private Double armor;
    @Column(name = "armorperlevel")
    private Double armorperlevel;
    @Column(name = "spellblock")
    private Double spellblock;
    @Column(name = "spellblockperlevel")
    private Double spellblockperlevel;
    @Column(name = "attackrange")
    private Double attackrange;
    @Column(name = "hpregen")
    private Double hpregen;
    @Column(name = "hpregenperlevel")
    private Double hpregenperlevel;
    @Column(name = "mpregen")
    private Double mpregen;
    @Column(name = "mpregenperlevel")
    private Double mpregenperlevel;
    @Column(name = "crit")
    private Double crit;
    @Column(name = "critperlevel")
    private Double critperlevel;
    @Column(name = "attackdamage")
    private Double attackdamage;
    @Column(name = "attackdamageperlevel")
    private Double attackdamageperlevel;
    @Column(name = "attackspeedperlevel")
    private Double attackspeedperlevel;
    @Column(name = "attackspeed")
    private Double attackspeed;

    @OneToMany(mappedBy = "stats")
    private Set<Champion> champion = new HashSet<>();

    public Stats() {
    }

    public Stats(ChampionItem championItem){
        leagueoflegendsproject.Models.LoLApi.Champions.Stats list = championItem.getStats();
        this.hp = list.getHp();
        this.hpperlevel = list.getHpperlevel();
        this.mp = list.getMp();
        this.mpperlevel = list.getMpperlevel();
        this.movespeed = list.getMovespeed();
        this.armor = list.getArmor();
        this.armorperlevel = list.getArmorperlevel();
        this.spellblock = list.getSpellblock();
        this.spellblockperlevel = list.getSpellblockperlevel();
        this.attackdamage = list.getAttackdamage();
        this.hpregen = list.getHpregen();
        this.hpregenperlevel = list.getHpregenperlevel();
        this.mpregen = list.getMpregen();
        this.mpregenperlevel = list.getMpregenperlevel();
        this.crit = list.getCrit();
        this.critperlevel = list.getCritperlevel();
        this.attackdamage = list.getAttackdamage();
        this.attackdamageperlevel = list.getAttackdamageperlevel();
        this.attackspeed = list.getAttackspeed();
        this.attackspeedperlevel = list.getAttackspeedperlevel();
    }

    public Stats(Integer id, Double hp, Double hpperlevel, Double mp, Double mpperlevel, Double movespeed, Double armor, Double armorperlevel, Double spellblock, Double spellblockperlevel, Double attackrange, Double hpregen, Double hpregenperlevel, Double mpregen, Double mpregenperlevel, Double crit, Double critperlevel, Double attackdamage, Double attackdamageperlevel, Double attackspeedperlevel, Double attackspeed, Set<Champion> champion) {
        this.id = id;
        this.hp = hp;
        this.hpperlevel = hpperlevel;
        this.mp = mp;
        this.mpperlevel = mpperlevel;
        this.movespeed = movespeed;
        this.armor = armor;
        this.armorperlevel = armorperlevel;
        this.spellblock = spellblock;
        this.spellblockperlevel = spellblockperlevel;
        this.attackrange = attackrange;
        this.hpregen = hpregen;
        this.hpregenperlevel = hpregenperlevel;
        this.mpregen = mpregen;
        this.mpregenperlevel = mpregenperlevel;
        this.crit = crit;
        this.critperlevel = critperlevel;
        this.attackdamage = attackdamage;
        this.attackdamageperlevel = attackdamageperlevel;
        this.attackspeedperlevel = attackspeedperlevel;
        this.attackspeed = attackspeed;
        this.champion = champion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public Double getHpperlevel() {
        return hpperlevel;
    }

    public void setHpperlevel(Double hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    public Double getMp() {
        return mp;
    }

    public void setMp(Double mp) {
        this.mp = mp;
    }

    public Double getMpperlevel() {
        return mpperlevel;
    }

    public void setMpperlevel(Double mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    public Double getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(Double movespeed) {
        this.movespeed = movespeed;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }

    public Double getArmorperlevel() {
        return armorperlevel;
    }

    public void setArmorperlevel(Double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    public Double getSpellblock() {
        return spellblock;
    }

    public void setSpellblock(Double spellblock) {
        this.spellblock = spellblock;
    }

    public Double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public void setSpellblockperlevel(Double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    public Double getAttackrange() {
        return attackrange;
    }

    public void setAttackrange(Double attackrange) {
        this.attackrange = attackrange;
    }

    public Double getHpregen() {
        return hpregen;
    }

    public void setHpregen(Double hpregen) {
        this.hpregen = hpregen;
    }

    public Double getHpregenperlevel() {
        return hpregenperlevel;
    }

    public void setHpregenperlevel(Double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    public Double getMpregen() {
        return mpregen;
    }

    public void setMpregen(Double mpregen) {
        this.mpregen = mpregen;
    }

    public Double getMpregenperlevel() {
        return mpregenperlevel;
    }

    public void setMpregenperlevel(Double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    public Double getCrit() {
        return crit;
    }

    public void setCrit(Double crit) {
        this.crit = crit;
    }

    public Double getCritperlevel() {
        return critperlevel;
    }

    public void setCritperlevel(Double critperlevel) {
        this.critperlevel = critperlevel;
    }

    public Double getAttackdamage() {
        return attackdamage;
    }

    public void setAttackdamage(Double attackdamage) {
        this.attackdamage = attackdamage;
    }

    public Double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public void setAttackdamageperlevel(Double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    public Double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public void setAttackspeedperlevel(Double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    public Double getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(Double attackspeed) {
        this.attackspeed = attackspeed;
    }

    public Set<Champion> getChampion() {
        return champion;
    }

    public void setChampion(Set<Champion> champion) {
        this.champion = champion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return Objects.equals(id, stats.id) &&
                Objects.equals(hp, stats.hp) &&
                Objects.equals(hpperlevel, stats.hpperlevel) &&
                Objects.equals(mp, stats.mp) &&
                Objects.equals(mpperlevel, stats.mpperlevel) &&
                Objects.equals(movespeed, stats.movespeed) &&
                Objects.equals(armor, stats.armor) &&
                Objects.equals(armorperlevel, stats.armorperlevel) &&
                Objects.equals(spellblock, stats.spellblock) &&
                Objects.equals(spellblockperlevel, stats.spellblockperlevel) &&
                Objects.equals(attackrange, stats.attackrange) &&
                Objects.equals(hpregen, stats.hpregen) &&
                Objects.equals(hpregenperlevel, stats.hpregenperlevel) &&
                Objects.equals(mpregen, stats.mpregen) &&
                Objects.equals(mpregenperlevel, stats.mpregenperlevel) &&
                Objects.equals(crit, stats.crit) &&
                Objects.equals(critperlevel, stats.critperlevel) &&
                Objects.equals(attackdamage, stats.attackdamage) &&
                Objects.equals(attackdamageperlevel, stats.attackdamageperlevel) &&
                Objects.equals(attackspeedperlevel, stats.attackspeedperlevel) &&
                Objects.equals(attackspeed, stats.attackspeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hp, hpperlevel, mp, mpperlevel, movespeed, armor, armorperlevel, spellblock, spellblockperlevel, attackrange, hpregen, hpregenperlevel, mpregen, mpregenperlevel, crit, critperlevel, attackdamage, attackdamageperlevel, attackspeedperlevel, attackspeed);
    }
}