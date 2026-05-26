package com.example.pncsegundoparcial00009123.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Space")
@Data
@Builder // Patron de diseño Builder
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos nuestros atributos
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;


    @Column(name = "capacity")
    private int capacity;

    @Column(name = "pricePerHour")
    private int pricePerHour;

    @Column(name="available")
    private boolean available;

    @Column(name = "floor")
    private int floor;

    @Column(name = "amenities")
    private String amenities;
}
