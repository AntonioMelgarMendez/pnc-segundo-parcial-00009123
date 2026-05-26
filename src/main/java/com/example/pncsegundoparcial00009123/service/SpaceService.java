package com.example.pncsegundoparcial00009123.service;

import com.example.pncsegundoparcial00009123.dto.request.SpaceDTORequest;
import com.example.pncsegundoparcial00009123.dto.response.SpaceDTOResponse;
import com.example.pncsegundoparcial00009123.entities.Space;
import com.example.pncsegundoparcial00009123.exception.SpaceCannotBeDeletedException;
import com.example.pncsegundoparcial00009123.exception.SpaceNotFound;
import com.example.pncsegundoparcial00009123.repository.SpaceRepository;
import com.example.pncsegundoparcial00009123.utils.SpaceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpaceService {

    private SpaceRepository spaceRepository;

    public void createSpace(SpaceDTORequest space){
        // Validar nombre y type no vacíos
        if (space.name() == null || space.name().isBlank()) {
            throw new IllegalArgumentException("El nombre del espacio no puede estar vacío");
        }
        if (space.type() == null || space.type().isBlank()) {
            throw new IllegalArgumentException("El tipo del espacio no puede estar vacío");
        }

        // Validar nombre único (sin importar mayúsculas/minúsculas)
        if (spaceRepository.existsByName(space.name().toLowerCase())){
            throw new IllegalArgumentException("Ya existe un espacio con el nombre '" + space.name() + "'");
        }

        // Validar precio mayor a cero
        if (space.pricePerHour() <= 0) {
            throw new IllegalArgumentException("El precio por hora debe ser mayor a cero");
        }

        // Validar capacidad mínima
        if (space.capacity() < 1) {
            throw new IllegalArgumentException("La capacidad debe ser al menos 1 persona");
        }

        // Validar piso no negativo
        if (space.floor() < 0) {
            throw new IllegalArgumentException("El número de piso no puede ser negativo");
        }

        spaceRepository.save(SpaceMapper.toEntity(space));
    }

    public SpaceDTOResponse findSpaceById(int id){
        return SpaceMapper.toResponse(spaceRepository.findById(id).orElseThrow(
                () -> new SpaceNotFound("Space not found with id " + id)
        ));
    }

    public List<Space> findAllSpace(Optional<String> type, Optional<Boolean> available){
        if (type.isPresent() && available.isPresent()) {
            return spaceRepository.findByTypeAndAvailable(type.get(), available.get());
        } else if (type.isPresent()) {
            return spaceRepository.findByType(type.get());
        } else if (available.isPresent()) {
            return spaceRepository.findByAvailable(available.get());
        } else {
            return spaceRepository.findAll();
        }
    }

    public List<Space> findAllSpace(){
        return spaceRepository.findAll();
    }
    public void deleteSpaceById(int id){
        Space space = spaceRepository.findById(id).orElseThrow(
                () -> new SpaceNotFound("Space not found with id " + id)
        );

        if (!space.isAvailable()) {
            throw new SpaceCannotBeDeletedException(
                "No se puede eliminar el espacio porque está marcado como no disponible (en uso o bloqueado)"
            );
        }

        spaceRepository.deleteById(id);
    }

    public void updateSpace(int id, SpaceDTORequest space){
        Space existingSpace = spaceRepository.findById(id).orElseThrow(
                () -> new SpaceNotFound("Space not found with id " + id)
        );

        // Validar nombre y type no vacios
        if (space.name() == null || space.name().isBlank()) {
            throw new IllegalArgumentException("El nombre del espacio no puede estar vacío");
        }
        if (space.type() == null || space.type().isBlank()) {
            throw new IllegalArgumentException("El tipo del espacio no puede estar vacío");
        }

        // Validar nombre único si cambio
        if (!existingSpace.getName().equalsIgnoreCase(space.name()) &&
            spaceRepository.existsByName(space.name().toLowerCase())) {
            throw new IllegalArgumentException("Ya existe otro espacio con el nombre '" + space.name() + "'");
        }

        // Validar precio mayor a cero
        if (space.pricePerHour() <= 0) {
            throw new IllegalArgumentException("El precio por hora debe ser mayor a cero");
        }

        // Validar capacidad mínima
        if (space.capacity() < 1) {
            throw new IllegalArgumentException("La capacidad debe ser al menos 1 persona");
        }

        // Validar piso no negativo
        if (space.floor() < 0) {
            throw new IllegalArgumentException("El número de piso no puede ser negativo");
        }

        Space spaceToUpdate = SpaceMapper.toEntity(space);
        spaceToUpdate.setId(id);
        spaceRepository.save(spaceToUpdate);
    }


}
