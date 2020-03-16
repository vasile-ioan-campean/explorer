package org.explorer.crew.api;

import org.explorer.commandcenter.feign.api.CrewFeignClient;
import org.explorer.commandcenter.model.Crew;
import org.explorer.crew.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/crew")
public class CrewController implements CrewFeignClient {

    private CrewService service;

    @Autowired
    public CrewController(CrewService service) {
        this.service = service;
    }

    /**
     * Retrieve all crew present in application storage
     *
     * @return http response entity containing the json formatted list of crews
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Crew>> getCrewList() {
        return ResponseEntity.ok(service.getCrewList());
    }

    /**
     * Retrieve data related to one crew based on the crewId property
     *
     * @param crewId the UUID assigned at the creation of the crew
     * @return success: http response entity containing the json formatted crew data
     *         failure: http response with status set as bad request
     */
    @GetMapping(value = "/{crewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crew> getCrew(@PathVariable String crewId) {
        return service.getCrew(crewId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
