package leagueoflegendsproject.DTOs;

import java.util.Objects;

public class MatchAchievementDTO {
    private String description;
    private String name;
    private AchievementType type;
    private double ratingScore;

    public MatchAchievementDTO(String name, String description, AchievementType type) {
        this.description = description;
        this.name = name;
        this.type = type;
    }

    public MatchAchievementDTO(String description, String name, AchievementType type, double ratingScore) {
        this.description = description;
        this.name = name;
        this.type = type;
        this.ratingScore = ratingScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AchievementType getType() {
        return type;
    }

    public void setType(AchievementType type) {
        this.type = type;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchAchievementDTO that = (MatchAchievementDTO) o;
        return Objects.equals(description, that.description) && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, type);
    }
}

