package org.explorer.crew.repository;

import org.explorer.commandcenter.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    Optional<Crew> findByCrewId(String crewId);
}
