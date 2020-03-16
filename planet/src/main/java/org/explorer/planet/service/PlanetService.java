package org.explorer.planet.service;

import org.explorer.commandcenter.model.Crew;
import org.explorer.commandcenter.model.Planet;
import org.explorer.commandcenter.model.PlanetStatus;
import org.explorer.planet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PlanetService {

    private PlanetRepository repository;

    @Autowired
    public PlanetService(PlanetRepository repository) {
        this.repository = repository;
    }

    public List<Planet> getPlanetList() {
        return repository.findAll();
    }

    public Optional<Planet> getPlanet(String planetId) {
        return repository.findByPlanetId(planetId);
    }

    public String savePlanet(String name, String image) {
        Planet planet = new Planet();
        planet.setName(name);
        planet.setImage(image);
        planet.setPlanetId(UUID.randomUUID().toString());
        planet.setStatus(PlanetStatus.TO_DO);

        return repository.saveAndFlush(planet).getPlanetId();
    }

    public boolean updatePlanetStatus(String planetId, String status) {
        AtomicBoolean success = new AtomicBoolean(false);

        repository.findByPlanetId(planetId).ifPresent(planet -> {
            try {
                planet.setStatus(PlanetStatus.find(status));
                repository.saveAndFlush(planet);
                success.set(true);
            } catch (NoSuchElementException e) {
              System.out.println(e.getLocalizedMessage());
            }
        });

        return success.get();
    }

    public boolean assignCrew(String planetId, Crew crew) {
        AtomicBoolean success = new AtomicBoolean(false);

        repository.findByPlanetId(planetId).ifPresent(planet -> {
            planet.setCrew(crew);
            repository.saveAndFlush(planet);
            success.set(true);
        });

        return success.get();
    }
}
