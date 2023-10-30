package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions;

import java.util.Objects;

public class Stats {

    private Double hp;
    private Double hpperlevel;
    private Double mp;
    private Double mpperlevel;
    private Double movespeed;
    private Double armor;
    private Double armorperlevel;
    private Double spellblock;
    private Double spellblockperlevel;
    private Double attackrange;
    private Double hpregen;
    private Double hpregenperlevel;
    private Double mpregen;
    private Double mpregenperlevel;
    private Double crit;
    private Double critperlevel;
    private Double attackdamage;
    private Double attackdamageperlevel;
    private Double attackspeedperlevel;
    private Double attackspeed;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return Objects.equals(hp, stats.hp) &&
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
        return Objects.hash(hp, hpperlevel, mp, mpperlevel, movespeed, armor, armorperlevel, spellblock, spellblockperlevel, attackrange, hpregen, hpregenperlevel, mpregen, mpregenperlevel, crit, critperlevel, attackdamage, attackdamageperlevel, attackspeedperlevel, attackspeed);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "hp=" + hp +
                ", hpperlevel=" + hpperlevel +
                ", mp=" + mp +
                ", mpperlevel=" + mpperlevel +
                ", movespeed=" + movespeed +
                ", armor=" + armor +
                ", armorperlevel=" + armorperlevel +
                ", spellblock=" + spellblock +
                ", spellblockperlevel=" + spellblockperlevel +
                ", attackrange=" + attackrange +
                ", hpregen=" + hpregen +
                ", hpregenperlevel=" + hpregenperlevel +
                ", mpregen=" + mpregen +
                ", mpregenperlevel=" + mpregenperlevel +
                ", crit=" + crit +
                ", critperlevel=" + critperlevel +
                ", attackdamage=" + attackdamage +
                ", attackdamageperlevel=" + attackdamageperlevel +
                ", attackspeedperlevel=" + attackspeedperlevel +
                ", attackspeed=" + attackspeed +
                '}';
    }
}
