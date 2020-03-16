package org.explorer.commandcenter.feign.api;

import org.explorer.commandcenter.model.Crew;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("crew")
public interface CrewFeignClient {

    @GetMapping("/")
    ResponseEntity<List<Crew>> getCrewList();

    @GetMapping("/{id}")
    ResponseEntity<Crew> getCrew(@PathVariable String id);
}
