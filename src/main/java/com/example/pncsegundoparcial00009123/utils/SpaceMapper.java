package com.example.pncsegundoparcial00009123.utils;

import com.example.pncsegundoparcial00009123.dto.request.SpaceDTORequest;
import com.example.pncsegundoparcial00009123.dto.response.SpaceDTOResponse;
import com.example.pncsegundoparcial00009123.entities.Space;

public class SpaceMapper {

    public static Space toEntity(SpaceDTORequest spaceDTORequest){
        return Space.builder()
                .name(spaceDTORequest.name().toLowerCase())
                .type(spaceDTORequest.type())
                .description(spaceDTORequest.description())
                .capacity(spaceDTORequest.capacity())
                .pricePerHour(spaceDTORequest.pricePerHour())
                .available(spaceDTORequest.available())
                .floor(spaceDTORequest.floor())
                .amenities(spaceDTORequest.amenities())
                .build();
    }

    public static SpaceDTOResponse toResponse(Space space){
        return new SpaceDTOResponse(
                space.getName(),
                space.getType(),
                space.getCapacity(),
                space.getPricePerHour(),
                space.isAvailable(),
                space.getFloor(),
                space.getDescription(),
                space.getAmenities()
        );
    }
}

