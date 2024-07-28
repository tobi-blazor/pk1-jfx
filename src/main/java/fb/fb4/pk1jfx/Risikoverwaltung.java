package fb.fb4.pk1jfx;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Risikoverwaltung {

    public List<Risiko> risikoList;
    public Risikoverwaltung() {
        risikoList = new ArrayList<Risiko>();
    }
    public void aufnehmen(Risiko risiko) {
        this.risikoList.add(risiko);
    }
    private static final long serialVersionUID = 12893781923178232L;

    public void zeigeRisiken() {
        Collections.sort(risikoList, new RisikoComparator());
        for(Risiko risiko : risikoList) {
            try {
                risiko.druckeDaten(System.out);
            }catch(IOException e) {
                System.out.println("Fehler beim OutputStream");
            }
        }
    }

    public void sucheRisikoMitMaxRueckstellung() {
        if(risikoList.size() == 0) {
            return;
        }
        Iterator<Risiko> iterator = risikoList.iterator();
        Risiko highestRückstellung = iterator.next();

        while(iterator.hasNext())
        {
            Risiko next = iterator.next();
            if(next.ermittleRueckstellung() > highestRückstellung.ermittleRueckstellung()) {
                highestRückstellung = next;
            }

        }
        try {
            highestRückstellung.druckeDaten(System.out);
        }catch(IOException e) {
            System.out.println("Fehler beim OutputStream");
        }
    }


    public float berechneSummeRueckstellungen() {
        float rückstellungSum = 0.0f;

        if(risikoList.size() == 0) {
            return rückstellungSum;
        }
        for(Risiko risiko : risikoList) {
            rückstellungSum += risiko.ermittleRueckstellung();
        }
        return rückstellungSum;
    }

    public void druckeRisikenInDatei(String dateiname) throws IOException {
        File f = new File(dateiname);
        try(FileOutputStream fos = new FileOutputStream(f)) {
            for(Risiko r : risikoList) {
                r.druckeDaten(fos);
            }
        }
    }

    public void serialisieren(File sfile) {
        try(FileOutputStream fos = new FileOutputStream(sfile); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(risikoList);
        }catch(Exception e) {
            System.out.println("Fehler bei serialisierung");
        }
    }
    public void deserialisieren(File sfile) {
        try(FileInputStream fis = new FileInputStream(sfile); ObjectInputStream ois = new ObjectInputStream(fis)) {
            risikoList = (List<Risiko>) ois.readObject();
        }catch (Exception e) {
            System.out.println("Fehler bei Deserialisierung");

        }
    }

}
