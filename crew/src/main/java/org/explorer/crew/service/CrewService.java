package org.explorer.crew.service;

import org.explorer.commandcenter.model.Crew;
import org.explorer.commandcenter.model.dto.CrewDTO;
import org.explorer.crew.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrewService {

    private CrewRepository repository;

    @Autowired
    public CrewService(CrewRepository repository) {
        this.repository = repository;
    }

    public List<Crew> getCrewList() {
        return repository.findAll();
    }

    public Optional<Crew> getCrew(String crewId) {
        return repository.findByCrewId(crewId);
    }

    @Transactional
    public String saveCrew(CrewDTO data) {
        Crew crew = new Crew();
        crew.setCrewId(UUID.randomUUID().toString());
        crew.setCaptain(data.getCaptain());
        crew.setRobots(data.getRobots());

        return repository.saveAndFlush(crew).getCrewId();
    }
}
