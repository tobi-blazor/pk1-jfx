package fb.fb4.pk1jfx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Risikoverwaltung risikoverwaltung = new Risikoverwaltung();

        RisikoverwaltungView risikoverwaltungView = new RisikoverwaltungView(risikoverwaltung, stage);
        risikoverwaltungView.showView();
        RisikoErfassungView risikoErfassungView = new RisikoErfassungView(risikoverwaltung, stage);
        risikoErfassungView.showView();

        AkzeptablesRisiko akzeptablesRisiko = new AkzeptablesRisiko(null, 0, 0);

        RisikoExtremErfassungView risikoExtremErfassungView = new RisikoExtremErfassungView(risikoverwaltung, stage, akzeptablesRisiko);
        risikoExtremErfassungView.showView();
        RisikoInakzeptabelErfassungView risikoInakzeptabelErfassungView = new RisikoInakzeptabelErfassungView(risikoverwaltung, stage, akzeptablesRisiko);
        risikoInakzeptabelErfassungView.showView();

    }

    public static void main(String[] args) {
        MenuUI menuUI = new MenuUI();
        menuUI.useDemoDaten();
        launch();

    }
}