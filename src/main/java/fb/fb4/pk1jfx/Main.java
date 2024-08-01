package fb.fb4.pk1jfx;

import fh.fb4.fachlogik.AkzeptablesRisiko;
import fh.fb4.fachlogik.ExtremesRisiko;
import fh.fb4.fachlogik.InakzeptablesRisiko;
import fh.fb4.fachlogik.Risikoverwaltung;
import fh.fb4.gui.RisikoErfassungView;
import fh.fb4.gui.RisikoExtremErfassungView;
import fh.fb4.gui.RisikoInakzeptabelErfassungView;
import fh.fb4.gui.RisikoverwaltungView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Risikoverwaltung risikoverwaltung = new Risikoverwaltung();
        AkzeptablesRisiko ar = new AkzeptablesRisiko();

        RisikoverwaltungView risikoverwaltungView = new RisikoverwaltungView(risikoverwaltung, stage);
        risikoverwaltungView.showView();
        RisikoErfassungView risikoErfassungView = new RisikoErfassungView(ar, stage);
        risikoErfassungView.showView();


        RisikoExtremErfassungView risikoExtremErfassungView = new RisikoExtremErfassungView(new ExtremesRisiko(), stage );
        risikoExtremErfassungView.showView();
        RisikoInakzeptabelErfassungView risikoInakzeptabelErfassungView = new RisikoInakzeptabelErfassungView(new InakzeptablesRisiko(), stage);
        risikoInakzeptabelErfassungView.showView();

    }

    public static void main(String[] args) {
        launch();

    }
}