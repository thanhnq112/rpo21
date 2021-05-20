package ru.iu3.rpo.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.iu3.rpo.backend.models.Country;
import ru.iu3.rpo.backend.models.Museum;
import ru.iu3.rpo.backend.repositories.MuseumRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MuseumController {

    @Autowired
    MuseumRepository museumRepository;

    //Read
    @GetMapping("/museums")
    public List<Museum> getAllMuseums() {
        return museumRepository.findAll();
    }

    //Create
    @PostMapping("/museums")
    public ResponseEntity<Object> createMuseum(@Validated @RequestBody Museum museum) {
        try {
            Museum nc = museumRepository.save(museum);
            return new ResponseEntity<Object>(nc, HttpStatus.OK);
        }
        catch (Exception ex) {
            String error;
            if (ex.getMessage().contains("museums.name_UNIQUE"))
                error = "museum already exists";
            else
                error = "undefined error";
            Map<String, String> map = new HashMap<>();
            map.put("error", error);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }

    //Create
//    @PostMapping("/countries")
//    public ResponseEntity<Object> createCountry(@Validated @RequestBody Country country) {
//        try {
//            Country nc = countryRepository.save(country);
//            return new ResponseEntity<Object>(nc, HttpStatus.OK);
//        }
//        catch (Exception ex) {
//            String error;
//            if (ex.getMessage().contains("countries.name_UNIQUE"))
//                error = "country already exists";
//            else
//                error = "undefined error";
//            Map<String, String> map = new HashMap<>();
//            map.put("error", error);
//            return new ResponseEntity<Object>(map, HttpStatus.OK);
//        }
//    }


    //Update
    @PutMapping("/museums/{id}")
    public ResponseEntity<Museum> updateMuseum(@PathVariable(value = "id") Long museumId, @Validated @RequestBody Museum museumDetails) {
        Museum museum = null;
        Optional<Museum> cc = museumRepository.findById(museumId);
        if (cc.isPresent()) {
            museum = cc.get();
            museum.name = museumDetails.name;
            museumRepository.save(museum);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "museum not found");
        }
        return ResponseEntity.ok(museum);
    }


    //Update
//    @PutMapping("/countries/{id}")
//    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") Long countryId, @Validated @RequestBody Country countryDetails) {
//        Country country = null;
//        Optional<Country> cc = countryRepository.findById(countryId);
//        if (cc.isPresent()) {
//            country = cc.get();
//            country.name = countryDetails.name;
//            countryRepository.save(country);
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "country not found");
//        }
//        return ResponseEntity.ok(country);
//    }

    //Delete
    @DeleteMapping("/museums/{id}")
    public Map<String, Boolean> deleteMuseum(@PathVariable(value = "id") Long museumId) {
        Optional<Museum> museum = museumRepository.findById(museumId);
        Map<String, Boolean> response = new HashMap<>();
        if (museum.isPresent()) {
            museumRepository.delete(museum.get());
            response.put("deleted", Boolean.TRUE);
        }
        else {
            response.put("deleted", Boolean.FALSE);
        }
        return response;
    }
}
