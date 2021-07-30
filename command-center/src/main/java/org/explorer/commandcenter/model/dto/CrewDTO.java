package org.explorer.commandcenter.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.explorer.commandcenter.model.Crew;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrewDTO {

    private String crewId;
    private String captain;
    private List<String> robots;

    public static CrewDTO map(Crew crew) {
        return CrewDTO.builder()
                .crewId(crew.getCrewId())
                .captain(crew.getCaptain())
                .robots(crew.getRobots())
                .build();
    }
}
