package org.explorer.crew.api;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.explorer.commandcenter.feign.api.CrewFeignClient;
import org.explorer.commandcenter.model.Crew;
import org.explorer.commandcenter.model.dto.CrewDTO;
import org.explorer.crew.service.CrewService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/crew")
@RequiredArgsConstructor
public class CrewController implements CrewFeignClient {

    private final CrewService service;

    /**
     * Retrieve all crew present in application storage
     *
     * @return http response entity containing the json formatted list of crews
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CrewDTO>> getCrewList() {
        return ResponseEntity.ok(service.getCrewList());
    }

    /**
     * Retrieve data related to one crew based on the crewId property
     *
     * @param crewId the UUID assigned at the creation of the crew
     * @return success: http response entity containing the json formatted crew data failure: http response with status set as bad request
     */
    @GetMapping(value = "/{crewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrewDTO> getCrew(@PathVariable String crewId) {
        return service.getCrew(crewId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Create a new crew
     *
     * @param data form data containing captain and robots for crew
     * @return the crewId of the created crew
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCrew(@ModelAttribute @NonNull CrewDTO data) {
        return ResponseEntity.ok(service.saveCrew(data));
    }

    @Override
    public Optional<Crew> getAssignedCrew(String crewId) {
        return service.getAssignedCrew(crewId);
    }
}
