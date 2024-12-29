package tn.esprit.tp_foyer.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.repository.EtudiantRepository;
import tn.esprit.tp_foyer.service.IEtudiantService;

import java.util.List;

@Tag(name = "Gestion Etudiant")
@RestController
@AllArgsConstructor
@RequestMapping(name = "/etudiant")
public class EtudiantRestController {
   IEtudiantService etudiantService;
    // http://localhost:8080/tpfoyer/etudiant/retrieve-all-foyers
    @Operation(description = "récupérer tous les etudiant de la base de données")
    @GetMapping("/retrieve-all-etudiant")
    public List<Etudiant> retrieveAllEtudiant() {
        List<Etudiant> listEtudiants = etudiantService.retriveAllEtudiants();
        return listEtudiants;
    }

    // http://localhost:8080/tpfoyer/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") long etId) {
        Etudiant etudiant = etudiantService.retriveEtudiant(etId);
        return etudiant;
    }

    // http://localhost:8080/tpfoyer/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    public List<Etudiant> addEtudiant(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }

    // http://localhost:8080/tpfoyer/etudiant/remove-etudiant/{etudiant-id}
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") long edId) {
        etudiantService.removeEtudiant(edId);
    }

    // http://localhost:8080/tpfoyer/etudiant/update-etudiant
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.updateEtudiant(e);
        return etudiant;
    }

}



