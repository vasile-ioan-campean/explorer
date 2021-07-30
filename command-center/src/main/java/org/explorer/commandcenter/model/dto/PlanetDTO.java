package org.explorer.commandcenter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.explorer.commandcenter.model.Planet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetDTO {

    private String planetId;
    private String name;
    private String image;
    private String status;
    private CrewDTO crewDTO;

    public static PlanetDTO map(Planet planet) {
        return PlanetDTO.builder()
                .name(planet.getName())
                .image(planet.getImage())
                .planetId(planet.getPlanetId())
                .status(planet.getStatus().getValue())
                .crewDTO(planet.getCrew() != null ? CrewDTO.map(planet.getCrew()) : null)
                .build();
    }
}
