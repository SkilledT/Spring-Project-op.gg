package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.DTOs.PreferedRoleDto;

public class PreferedRoleDtoBuilder {
    private PreferedRoleDto preferedRoleDto;


    public PreferedRoleDtoBuilder() {
        preferedRoleDto = new PreferedRoleDto();
    }

    public PreferedRoleDtoBuilder withPickRatio(double pickRatio) {
        this.preferedRoleDto.setPickRatio(pickRatio);
        return this;
    }

    public PreferedRoleDtoBuilder withWinRatio(double winRatio) {
        this.preferedRoleDto.setWinRatio(winRatio);
        return this;
    }

    public PreferedRoleDtoBuilder withName(Constants.MatchParticipantConstants.IndividualPosition name) {
        this.preferedRoleDto.setName(name);
        return this;
    }

    public PreferedRoleDtoBuilder withIconUrl() {
        this.preferedRoleDto.setIconUrl();
        return this;
    }

    public PreferedRoleDto build() {
        return this.preferedRoleDto;
    }
}
