package com.app.coworkingspace.Service;

import com.app.coworkingspace.entity.Spaces;
import com.app.coworkingspace.repository.SpacesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpaceService {

    private final SpacesRepository repository;

    public SpaceService(SpacesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addSpace() {
        Spaces space = new Spaces();
        space.setReserved(false);
        space.setUsername("-");
        repository.save(space);
    }

    @Transactional
    public void deleteSpace(int id) {
        repository.deleteById(id);
    }

    public List<Spaces> getAllSpaces() {
        return repository.findAll();
    }

    public List<Spaces> getAvailableSpaces() {
        return repository.findEmptyTables();
    }

    @Transactional
    public String reserveSpace(int id, String username) {
        if (repository.countByUsername(username) >= 2) {
            return "Maximum reservations reached.";
        }
        return repository.findById(id)
                .map(space -> {
                    if (!space.isReserved()) {
                        space.setReserved(true);
                        space.setUsername(username);
                        repository.save(space);
                        return "Reserved!";
                    } else {
                        return "Already reserved.";
                    }
                }).orElse("Space not found.");
    }

    @Transactional
    public String cancelReservation(int id, String username) {
        return repository.findById(id)
                .map(space -> {
                    if (space.isReserved() && username.equals(space.getUsername())) {
                        space.setReserved(false);
                        space.setUsername("-");
                        repository.save(space);
                        return "Reservation cancelled.";
                    } else {
                        return "No reservation found for user.";
                    }
                }).orElse("Space not found.");
    }

    public List<Spaces> myReservedSpaces(String username) {
        return repository.findByUsername(username);
    }
}
