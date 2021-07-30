package org.explorer.planet.repository;

import java.util.Optional;
import org.explorer.commandcenter.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> findByPlanetId(String planetId);
}
