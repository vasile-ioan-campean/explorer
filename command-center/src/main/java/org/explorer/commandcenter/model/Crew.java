package org.explorer.commandcenter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String crewId;
    private String captain;
    @ElementCollection
    private List<String> robots ;

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public List<String> getRobots() {
        return robots;
    }

    public void setRobots(List<String> robots) {
        this.robots = robots;
    }
}
