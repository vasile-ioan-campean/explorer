package org.explorer.commandcenter.model.dto;

import org.explorer.commandcenter.model.Planet;

public class PlanetDTO {
    private String planetId;
    private String name;
    private String image;
    private String status;
    private CrewDTO crewDTO;

    public String getPlanetId() {
        return planetId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public CrewDTO getCrewDTO() {
        return crewDTO;
    }

    public void setPlanetId(String planetId) {
        this.planetId = planetId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCrewDTO(CrewDTO crewDTO) {
        this.crewDTO = crewDTO;
    }

    public static PlanetDTO map(Planet planet) {
        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setName(planet.getName());
        planetDTO.setImage(planet.getImage());
        planetDTO.setPlanetId(planet.getPlanetId());
        planetDTO.setStatus(planet.getStatus().getValue());
        if (planet.getCrew() != null) {
            planetDTO.setCrewDTO(CrewDTO.map(planet.getCrew()));
        }

        return planetDTO;
    }
}
