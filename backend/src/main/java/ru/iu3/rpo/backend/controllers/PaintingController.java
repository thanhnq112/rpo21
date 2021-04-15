package ru.iu3.rpo.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.iu3.rpo.backend.models.Painting;
import ru.iu3.rpo.backend.repositories.PaintingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PaintingController {
    @Autowired
    PaintingRepository paintingRepository;

    //Read
    @GetMapping("/paintings")
    public List<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }

    //Create
    @PostMapping("/paintings")
    public ResponseEntity<Object> createPainting(@Validated @RequestBody Painting painting) {
        try {
            Painting nc = paintingRepository.save(painting);
            return new ResponseEntity<Object>(nc, HttpStatus.OK);
        }
        catch (Exception ex) {
            String error;
            if (ex.getMessage().contains("paintings.name_UNIQUE"))
                error = "painting already exists";
            else
                error = "undefined error";
            Map<String, String> map = new HashMap<>();
            map.put("error", error);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }

    //Update
    @PutMapping("/paintings/{id}")
    public ResponseEntity<Painting> updatePainting(@PathVariable(value = "id") Long paintingId, @Validated @RequestBody Painting paintingDetails) {
        Painting painting = null;
        Optional<Painting> cc = paintingRepository.findById(paintingId);
        if (cc.isPresent()) {
            painting = cc.get();
            painting.name = paintingDetails.name;
            paintingRepository.save(painting);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "painting not found");
        }
        return ResponseEntity.ok(painting);
    }

    //Delete
    @DeleteMapping("/paintings/{id}")
    public Map<String, Boolean> deletePainting(@PathVariable(value = "id") Long paintingId) {
        Optional<Painting> painting = paintingRepository.findById(paintingId);
        Map<String, Boolean> response = new HashMap<>();
        if (painting.isPresent()) {
            paintingRepository.delete(painting.get());
            response.put("deleted", Boolean.TRUE);
        }
        else {
            response.put("deleted", Boolean.FALSE);
        }
        return response;
    }
}
