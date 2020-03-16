package org.explorer.planet.api;

import org.explorer.commandcenter.feign.api.CrewFeignClient;
import org.explorer.commandcenter.model.dto.PlanetDTO;
import org.explorer.planet.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/planet")
public class PlanetController {

    private CrewFeignClient crewFeignClient;
    private PlanetService service;

    @Autowired
    public PlanetController(PlanetService service, CrewFeignClient crewFeignClient) {
        this.service = service;
        this.crewFeignClient = crewFeignClient;
    }

    /**
     * Retrieve all planets present in application storage
     *
     * @return http response entity containing the json formatted list of planets
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanetDTO>> getPlanetList() {
        return ResponseEntity.ok(service.getPlanetList());
    }

    /**
     * Retrieve data related to one crew based on the planetId property
     *
     * @param planetId the UUID assigned at the creation of the planet
     * @return success: http response entity containing the json formatted planet data
     *         failure: http response with status set as bad request
     */
    @GetMapping(value = "/{planetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetDTO> getPlanet(@PathVariable String planetId) {
        return service.getPlanet(planetId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Update status of a planet to one of the elements found in {@link org.explorer.commandcenter.model.PlanetStatus} enum
     *
     * @param planetId the UUID assigned at the creation of the planet
     * @param status the value property of the {@link org.explorer.commandcenter.model.PlanetStatus} that will be assigned to the planet
     * @return success: http response entity with status set as OK
     *         failure: http response with status set as unprocessable entity
     */
    @PutMapping(value = "/{planetId}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePlanetStatus(@PathVariable String planetId, @RequestParam  String status) {
        return service.updatePlanetStatus(planetId, status) ? ResponseEntity.ok().build() : ResponseEntity.unprocessableEntity().build();
    }

    /**
     * Assign a crew to a planet for exploration
     *
     * @param planetId the UUID assigned at the creation of the planet
     * @param crewId the UUID assigned at the creation of the crew
     * @return success: http response entity with status set as OK
     *         failure: http response with status set as unprocessable entity
     */
    @PutMapping(value = "/{planetId}/crew", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> assignCrew(@PathVariable String planetId, @RequestParam  String crewId) {
        return service.assignCrew(planetId, crewFeignClient.getAssignedCrew(crewId)) ? ResponseEntity.ok().build() : ResponseEntity.unprocessableEntity().build();
    }

    /**
     * Create a new planet
     *
     * @param data form data containing the name and image for the new planet
     * @return the planetId for the created planet
     */
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPlanet(@ModelAttribute @NonNull PlanetDTO data) {
        return ResponseEntity.ok(service.savePlanet(data));
    }
}
