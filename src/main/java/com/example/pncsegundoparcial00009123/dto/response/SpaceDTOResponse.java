package com.example.pncsegundoparcial00009123.dto.response;

import jakarta.persistence.Column;

public record SpaceDTOResponse(
        String name,
        String type,
        int capacity,
        int pricePerHour,
        boolean available,
        int floor,
        String description,
        String amenities

) {

}