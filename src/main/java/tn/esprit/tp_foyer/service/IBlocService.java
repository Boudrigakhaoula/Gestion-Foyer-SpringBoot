package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Bloc;

import java.util.List;

public interface IBlocService {
    public List<Bloc> retrieveBlocs();
    public Bloc updateBloc(Bloc bloc);
    public Bloc addBloc(Bloc bloc);
    public Bloc retrieveBloc(long idBloc);
    public void deleteBloc(long idBloc);
    public Bloc affecterChambresABloc(List<Long> numChambres, long idBloc);
}

