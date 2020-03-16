package org.explorer.commandcenter.feign.api;

import org.explorer.commandcenter.model.Crew;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("crew")
public interface CrewFeignClient {

    /**
     * Used to retrieve crew data for planet exploration assigment
     *
     * @param crewId the UUID of the crew to be deployed
     * @return crew data
     */
    @GetMapping("/{crewId}/assign")
    Optional<Crew> getAssignedCrew(@PathVariable String crewId);
}
