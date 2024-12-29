package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.service.IUniversiteService;

import java.util.List;

@Tag(name = "Gestion Universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversitesRestController {
  IUniversiteService universiteService;


    // http://localhost:8080/foyer/universite/retrieve-all-universites
    @Operation(description = "récupérer tous les Universites de la base de données")
    @GetMapping("/retrieve-all-universite")
    public List<Universite> retrieveAllUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversite();
        return listUniversites;
    }

    // http://localhost:8080/foyer/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") long idUniversite) {
        Universite universite = universiteService.retrieveUniversite(idUniversite);
        return universite;
    }

    // http://localhost:8080/foyer/universite/add-universite
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;

    }

    // http://localhost:8080/foyer/universite/update-universite
    @PutMapping("/update-universite")
    public Universite updateUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.updateUniversite(u);
        return universite;
    }
    // http://localhost:8080/foyer/universite/affecterFoyer
    @PostMapping("/affecterFoyer")
    public Universite affecterFoyerAUniversite(@RequestParam long idFoyer, @RequestParam String nomUniversite){
        Universite universite = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
        return universite;
    }
    // http://localhost:8080/foyer/universite/desaffecterFoyer/{universite-id}
    @DeleteMapping("/desaffecterFoyer/{universite-id}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("universite-id") long idUniversite) {
        Universite universite = universiteService.desaffecterFoyerAUniversite(idUniversite);
        return universite;
    }

}


