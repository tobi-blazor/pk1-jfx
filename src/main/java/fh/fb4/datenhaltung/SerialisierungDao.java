package fh.fb4.datenhaltung;

import fh.fb4.fachlogik.Risiko;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SerialisierungDao implements IDao{
    @Override
    public void speichern(List<Risiko> liste) throws PersistenzException {
        String dateiname = "savedData.ser";
        File f = new File(dateiname);
        try(FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(liste);
        }catch(Exception e) {
            throw new PersistenzException("Serialisierungs fehler");
        }
    }

    @Override
    public List<Risiko> laden() throws PersistenzException {
        List<Risiko> risikoList;

        String dateiname = "savedData.ser";
        File f = new File(dateiname);
        try(FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            risikoList = (List<Risiko>) ois.readObject();
        }catch (Exception e) {
            throw new PersistenzException("Deserialisierung fehler");
        }
        return risikoList;
    }
}
