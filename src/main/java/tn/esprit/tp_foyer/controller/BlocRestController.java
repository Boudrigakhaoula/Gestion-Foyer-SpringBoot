package tn.esprit.tp_foyer.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.service.IBlocService;

import java.util.List;

@Tag(name = "Gestion Foyer")
@RestController
@AllArgsConstructor
@RequestMapping(name = "/bloc")
public class BlocRestController {
    IBlocService blocService;

    // http://localhost:8080/tpfoyer/bloc/retrieve-all-blocs
    @Operation(description = "récupérer tous les bloc de la base de données")
    @GetMapping("/retrieve-all-bloc")
    public List<Bloc> retrieveBlocs() {
        List<Bloc> listBlocs = blocService.retrieveBlocs();
        return listBlocs;

    }

    // http://localhost:8080/tpfoyer/bloc/retrieve-bloc/8
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") long idBloc) {
        Bloc bloc = blocService.retrieveBloc(idBloc);
        return bloc;
    }

    // http://localhost:8080/tpfoyer/bloc/add-bloc
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.addBloc(b);
        return bloc;
    }

    // http://localhost:8080/tpfoyer/bloc/delete-bloc/{bloc-id}
    @DeleteMapping("/delete-bloc/{bloc-id}")
    public void deleteBloc (@PathVariable("bloc-id") long idBloc) {
        blocService.deleteBloc(idBloc);
    }

    // http://localhost:8080/tpfoyer/bloc/update-bloc
    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.updateBloc(b);
        return bloc;
    }

    // http://localhost:8080/tpfoyer/bloc/affecterChambres
    @PostMapping("/affecterChambres")
    public Bloc affecterChambresABloc(@RequestParam List<Long> numChambres, @RequestParam long idBloc) {
        Bloc bloc = blocService.affecterChambresABloc(numChambres, idBloc);
        return bloc;
    }

}

