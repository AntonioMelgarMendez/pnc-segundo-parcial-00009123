package com.example.pncsegundoparcial00009123.repository;

import com.example.pncsegundoparcial00009123.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {

    List<Space> findByType(String type);

    List<Space> findByAvailable(boolean available);

    List<Space> findByTypeAndAvailable(String type, boolean available);

    boolean existsByName(String name);

    Space findByName(String name);

}
