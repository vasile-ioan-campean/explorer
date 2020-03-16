package org.explorer.commandcenter.model.dto;

import org.explorer.commandcenter.model.Crew;

import java.util.List;

public class CrewDTO {
    private String crewId;
    private String captain;
    private List<String> robots;

    public String getCrewId() {
        return crewId;
    }

    public String getCaptain() {
        return captain;
    }

    public List<String> getRobots() {
        return robots;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setRobots(List<String> robots) {
        this.robots = robots;
    }

    public static CrewDTO map(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setCrewId(crew.getCrewId());
        crewDTO.setCaptain(crew.getCaptain());
        crewDTO.setRobots(crew.getRobots());

        return crewDTO;
    }
}
