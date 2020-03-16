package org.explorer.crew.service;

import org.explorer.commandcenter.model.Crew;
import org.explorer.crew.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
