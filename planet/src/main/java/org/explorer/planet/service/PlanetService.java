package org.explorer.planet.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.explorer.commandcenter.model.Crew;
import org.explorer.commandcenter.model.Planet;
import org.explorer.commandcenter.model.PlanetStatus;
import org.explorer.commandcenter.model.dto.PlanetDTO;
import org.explorer.planet.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository repository;

    public List<PlanetDTO> getPlanetList() {
        return repository.findAll().stream().map(PlanetDTO::map).collect(Collectors.toList());
    }

    public Optional<PlanetDTO> getPlanet(String planetId) {
        return repository.findByPlanetId(planetId).map(PlanetDTO::map);
    }

    @Transactional
    public String savePlanet(PlanetDTO data) {
        var planet = Planet.builder()
                .planetId(UUID.randomUUID().toString())
                .name(data.getName())
                .image(data.getImage())
                .status(PlanetStatus.TO_DO)
                .build();

        return repository.saveAndFlush(planet).getPlanetId();
    }

    @Transactional
    public boolean updatePlanetStatus(String planetId, String status) {
        log.info(String.format("Changing status for planet=%s to %s", planetId, status));
        AtomicBoolean success = new AtomicBoolean(false);

        repository.findByPlanetId(planetId).ifPresent(planet -> {
            try {
                planet.setStatus(PlanetStatus.find(status));
                repository.saveAndFlush(planet);
                success.set(true);
            } catch (NoSuchElementException e) {
                log.error(e.getLocalizedMessage());
            }
        });

        return success.get();
    }

    @Transactional
    public boolean assignCrew(String planetId, Optional<Crew> crew) {
        AtomicBoolean success = new AtomicBoolean(false);

        crew.ifPresent(value ->
                repository.findByPlanetId(planetId).ifPresent(planet -> {
                    planet.setCrew(value);
                    repository.save(planet);
                    success.set(true);
                })
        );

        return success.get();
    }
}
