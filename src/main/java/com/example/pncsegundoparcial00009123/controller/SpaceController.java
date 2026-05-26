package com.example.pncsegundoparcial00009123.controller;
import com.example.pncsegundoparcial00009123.dto.GeneralResponse;
import com.example.pncsegundoparcial00009123.dto.request.SpaceDTORequest;
import com.example.pncsegundoparcial00009123.service.SpaceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/spaces")
@AllArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping
    public ResponseEntity<GeneralResponse> findAll(
            @RequestParam(required = false) Optional<String> type,
            @RequestParam(required = false) Optional<Boolean> available){
        return ResponseEntity.ok(GeneralResponse.builder()
                        .data(spaceService.findAllSpace(type, available))
                        .message("Espacios encontrados")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getSpaceById(@PathVariable int id) {
        return ResponseEntity.ok(GeneralResponse.builder()
                        .data(spaceService.findSpaceById(id))
                        .message("Espacio encontrado con id: " + id)
                .build());
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createSpace(@Valid @RequestBody SpaceDTORequest space) {
        spaceService.createSpace(space);
        return ResponseEntity.ok(GeneralResponse.builder()
                        .data(space)
                        .message("Espacio creado exitosamente")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateSpace(@PathVariable int id,@Valid @RequestBody SpaceDTORequest space) {
        spaceService.updateSpace(id,space);
        return ResponseEntity.ok(GeneralResponse.builder()
                        .data(space)
                        .message("Espacio actualizado exitosamente")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteSpace(@PathVariable int id) {
        spaceService.deleteSpaceById(id);
        return ResponseEntity.ok(GeneralResponse.builder()
                .data(null)
                .message("Espacio eliminado exitosamente")
                .build());
    }

}
