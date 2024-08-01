package fh.fb4.fachlogik;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ExtremesRisiko extends InakzeptablesRisiko {
    // rückstellung = versicherungsbeitrag

    private float versicherungsbeitrag;
    public ExtremesRisiko() {
        super();
    }
    ExtremesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall, String massnahme, float versicherungsbeitrag) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall, massnahme);
        this.versicherungsbeitrag = versicherungsbeitrag;
    }

    public float getVersicherungsbeitrag() {
        return this.versicherungsbeitrag;
    }
    public void setVersicherungsbeitrag(float versicherungsbeitrag){
        this.versicherungsbeitrag = versicherungsbeitrag;
    }

    public float ermittleRueckstellung() {
        return versicherungsbeitrag;
    }
    @Override
    public String toString() {
        return String.format("Id %d Extremes Risiko \"%s\" aus %d/%d; Versicherungsbeitrag %.2f; Maßnahme \"%s\"\n",
                this.getId(),
                this.getBezeichnung(),
                this.getErstelldatum().getMonthValue(),
                this.getErstelldatum().getYear(),
                versicherungsbeitrag,
                this.getMassnahme());
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
        ExtremesRisiko risiko = (ExtremesRisiko) object;
        if(this.getVersicherungsbeitrag() != risiko.getVersicherungsbeitrag())
            return false;
        return true;
    }

}
