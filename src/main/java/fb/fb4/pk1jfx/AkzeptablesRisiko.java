package fb.fb4.pk1jfx;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AkzeptablesRisiko extends Risiko {
    // keine rückstellung einplanen
    AkzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
    }
    public float ermittleRueckstellung() {
        return 0.0f;
    }
    @Override
    public String toString() {
        return String.format("Id %d Akzeptables Risiko \"%s\" aus %d/%d; Risikowert %.2f; Rückstellung %.2f\n",
                this.getId(),
                this.getBezeichnung(),
                this.getErstelldatum().getMonthValue(),
                this.getErstelldatum().getYear(),
                this.berechneRisikowert(),
                ermittleRueckstellung());
    }

    public void druckeDaten(OutputStream os) throws IOException {
         String s = this.toString();

        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write(s.toCharArray());
        osw.flush();
    }
}
