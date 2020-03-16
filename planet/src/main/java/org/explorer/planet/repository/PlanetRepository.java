package org.explorer.planet.repository;

import org.explorer.commandcenter.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<Planet> findByPlanetId(String planetId);
//    private  List<Planet> planets = new ArrayList<>();
//
//    public List<Planet> getPlanetList() {
//        return planets;
//    }
//
//    public Optional<Planet> getPlanet(String planetId) {
//        return planets.stream().filter(e -> e.getPlanetId().equals(planetId)).findAny();
//    }
//
//    public boolean updatePlanetStatus(String planetId, PlanetStatus status) {
//        boolean success = false;
//        Optional<Planet> planet = planets.stream().filter(e -> e.getPlanetId().equals(planetId)).findAny();
//        if (planet.isPresent()) {
//            planet.get().setStatus(status);
//            success = true;
//        }
//        return success;
//    }
//
//    public boolean assignCrew(String planetId, Crew crew) {
//        boolean success = false;
//        Optional<Planet> planet = planets.stream().filter(e -> e.getPlanetId().equals(planetId)).findAny();
//        if (planet.isPresent()) {
//            planet.get().setCrew(crew);
//            success = true;
//        }
//        return success;
//    }
//
//    public String addPlanet(Planet planet) {
//        planets.add(planet);
//        return planet.getPlanetId();
//    }
}
