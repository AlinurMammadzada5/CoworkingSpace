package com.app.coworkingspace.repository;

import com.app.coworkingspace.entity.Spaces;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpacesRepository extends JpaRepository<Spaces, Integer> {
    List<Spaces> findEmptyTables();
    List<Spaces> findByUsername(String username);
    long countByUsername(String username);
}
