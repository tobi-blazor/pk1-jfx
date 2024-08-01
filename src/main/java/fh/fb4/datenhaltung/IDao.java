package fh.fb4.datenhaltung;

import fh.fb4.fachlogik.Risiko;

import java.util.List;

public interface IDao {
    void speichern(List<Risiko> liste) throws PersistenzException;
    List<Risiko> laden() throws PersistenzException;
}
