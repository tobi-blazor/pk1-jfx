package fb.fb4.pk1jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Risikoverwaltung risikoverwaltung = new Risikoverwaltung();

        RisikoverwaltungView risikoverwaltungView = new RisikoverwaltungView(risikoverwaltung, stage);
        stage.show();
        RisikoErfassungView risikoErfassungView = new RisikoErfassungView(risikoverwaltung, stage);
        risikoErfassungView.showView();

        AkzeptablesRisiko akzeptablesRisiko = new AkzeptablesRisiko(null, 0, 0);

        RisikoExtremErfassungView risikoExtremErfassungView = new RisikoExtremErfassungView(risikoverwaltung, stage, akzeptablesRisiko);
        risikoExtremErfassungView.showView();
        RisikoInakzeptabelErfassungView risikoInakzeptabelErfassungView = new RisikoInakzeptabelErfassungView(risikoverwaltung, stage, akzeptablesRisiko);
        risikoInakzeptabelErfassungView.showView();

    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.useDemoDaten();
        launch();

    }
}