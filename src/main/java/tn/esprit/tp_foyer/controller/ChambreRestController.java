package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.service.IChambreService;

import java.util.List;

@Tag(name = "Gestion Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {
    IChambreService chambreService;


    // http://localhost:8080/foyer/chambre/retrieve-all-chambres
    @Operation(description = "récupérer tous les chambres de la base de données")
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> retrieveAllChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }

    // http://localhost:8080/foyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    // http://localhost:8080/foyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8080/foyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") long chId) {
        chambreService.removeChambre(chId);
    }

    // http://localhost:8080/foyer/chambre/modify-chambre
    @PutMapping("/update-chambre")
    public Chambre updateChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.updateChambre(c); return chambre;
    }
    // http://localhost:8080/tpfoyer/chambres/getChambresParNomUniversite
    @GetMapping("/getChambresParNomUniversite")
    public List<Chambre> getChambresParNomUniversite(@RequestParam String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

    // http://localhost:8080/foyer/chambre/getChambresParBlocEtType
    @GetMapping("/getChambresParBlocEtType")
    public List<Chambre> getChambresParBlocEtType(@RequestParam long idBloc, @RequestParam TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }
    @GetMapping("/getChambresNonReserveParNomUniversiteEtTypeChambre")
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@RequestParam String nomUniversite, @RequestParam TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }
    @GetMapping("/getChambresNonReserveesPourToutesLesUniversites")
    public List<Chambre> getChambresNonReserveesPourToutesLesUniversites() {
        return chambreService.getChambresNonReserveesPourToutesLesUniversites();
    }

}
