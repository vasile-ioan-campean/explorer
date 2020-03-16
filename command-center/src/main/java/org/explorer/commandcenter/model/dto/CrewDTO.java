package org.explorer.commandcenter.model.dto;

import java.util.List;

public class CrewDTO {
    private String captain;
    private List<String> robots;

    public String getCaptain() {
        return captain;
    }

    public List<String> getRobots() {
        return robots;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setRobots(List<String> robots) {
        this.robots = robots;
    }
}
