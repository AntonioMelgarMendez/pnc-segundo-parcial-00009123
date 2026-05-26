package com.example.pncsegundoparcial00009123.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SpaceDTORequest(

        @NotBlank(message = "El nombre no puede estar vacío")
        String name,

        @NotBlank(message = "El tipo no puede estar vacío")
        String type,

        @Min(value = 1, message = "La capacidad debe ser mayor o igual a 1")
        int capacity,

        @Min(value = 1, message = "El precio por hora debe ser mayor a cero")
        int pricePerHour,

        @NotNull(message = "La disponibilidad no puede ser nula")
        boolean available,

        @Min(value = 0, message = "El número de piso no puede ser negativo")
        int floor,

        String description,

        @NotBlank(message = "Las amenidades no pueden estar vacías")
        String amenities

) {
}
