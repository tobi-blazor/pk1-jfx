package fh.fb4.fachlogik;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Risiko implements Serializable {
    private static int idCount = 0;
    private final int id;
    private String bezeichnung;
    private float eintrittswahrscheinlichkeit;
    private float kosten_im_schadensfall;
    private LocalDate erstellungsdatum;
    Risiko() {
        this.id = idCount++;
    }
    Risiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall){
        this.id = idCount++;
        this.bezeichnung = bezeichnung;
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
        this.kosten_im_schadensfall = kosten_im_schadensfall;
        this.erstellungsdatum = LocalDate.now();
    }

    public float berechneRisikowert() {
        return eintrittswahrscheinlichkeit * kosten_im_schadensfall;
    }

    public abstract float ermittleRueckstellung();

    public abstract void druckeDaten(OutputStream os) throws IOException;

    public int getId() {
        return id;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public LocalDate getErstelldatum() {
        return erstellungsdatum;
    }
    public void setErstellungsdatum(LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }
    public float getKosten_im_schadensfall() {return kosten_im_schadensfall;}
    public void setKosten_im_schadensfall(float kosten_im_schadensfall) {
        this.kosten_im_schadensfall = kosten_im_schadensfall;
    }
    public float getEintrittswahrscheinlichkeit() {return eintrittswahrscheinlichkeit;}
    public void setEintrittswahrscheinlichkeit(float eintrittswahrscheinlichkeit) {
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
    }
    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null) {
            return false;
        }

        if(object.getClass() != this.getClass()) {
            return false;
        }

        AkzeptablesRisiko risiko = (AkzeptablesRisiko) object;

        if(this.getId() != risiko.getId())
            return false;
        if(!this.getBezeichnung().equals(risiko.getBezeichnung()))
            return false;
        if(this.getEintrittswahrscheinlichkeit() != risiko.getEintrittswahrscheinlichkeit())
            return false;
        if(this.getKosten_im_schadensfall() != risiko.getKosten_im_schadensfall())
            return false;
        if(!this.getErstelldatum().equals(risiko.getErstelldatum()))
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getBezeichnung());
    }



}
