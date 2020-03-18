package org.explorer.planet.service;

import lombok.extern.slf4j.Slf4j;
import org.explorer.commandcenter.model.Crew;
import org.explorer.commandcenter.model.Planet;
import org.explorer.commandcenter.model.PlanetStatus;
import org.explorer.commandcenter.model.dto.PlanetDTO;
import org.explorer.planet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlanetService {

    private PlanetRepository repository;

    @Autowired
    public PlanetService(PlanetRepository repository) {
        this.repository = repository;
    }

    public List<PlanetDTO> getPlanetList() {
        return repository.findAll().stream().map(PlanetDTO::map).collect(Collectors.toList());
    }

    public Optional<PlanetDTO> getPlanet(String planetId) {
        return repository.findByPlanetId(planetId).map(PlanetDTO::map);
    }

    @Transactional
    public String savePlanet(PlanetDTO data) {
        Planet planet = new Planet();
        planet.setPlanetId(UUID.randomUUID().toString());
        planet.setName(data.getName());
        planet.setImage(data.getImage());
        planet.setStatus(PlanetStatus.TO_DO);

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
