package fh.fb4.fachlogik;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class InakzeptablesRisiko extends Risiko {
    // rückstellung = risikowert
    private String massnahme;
    public InakzeptablesRisiko() {
        super();
    }
    InakzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall, String massnahme) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
        this.massnahme = massnahme;
    }
    public String getMassnahme() {
        return massnahme;
    }
    public void setMassnahme(String massnahme){
        this.massnahme = massnahme;
    }
    public float ermittleRueckstellung() {
        return berechneRisikowert();
    }

    @Override
    public String toString() {
        return String.format("Id %d Inakzeptables Risiko \"%s\" aus %d/%d; Risikowert %.2f; Rückstellung %.2f; Maßnahme \"%s\"\n",
                this.getId(),
                this.getBezeichnung(),
                this.getErstelldatum().getMonthValue(),
                this.getErstelldatum().getYear(),
                this.berechneRisikowert(),
                ermittleRueckstellung(),
                massnahme);
    }

    public void druckeDaten(OutputStream os) throws IOException {
        String s = this.toString();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write(s.toCharArray());
        osw.flush();
    }

    @Override
    public boolean equals(Object object) {
        if(!super.equals(object))
            return false;
        InakzeptablesRisiko risiko = (InakzeptablesRisiko) object;
        if(!this.getMassnahme().equals(risiko.getMassnahme()))
            return false;
        return true;
    }


}
