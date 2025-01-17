package fh.fb4.gui;

import fh.fb4.fachlogik.AkzeptablesRisiko;
import fh.fb4.fachlogik.InakzeptablesRisiko;
import fh.fb4.fachlogik.Risiko;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;
import java.time.LocalDate;

public class RisikoInakzeptabelErfassungView extends Stage {
    InakzeptablesRisiko risiko;
    SimpleStringProperty massnahmen;
    public RisikoInakzeptabelErfassungView(Risiko risiko, Stage stage) {
        if(risiko.getClass().equals(AkzeptablesRisiko.class)) {
            String bezeichnung = risiko.getBezeichnung();
            float eintrittswahrscheinlichkeit = risiko.getEintrittswahrscheinlichkeit();
            float kostenimschadensfall = risiko.getKosten_im_schadensfall();
            LocalDate erstelldatum = risiko.getErstelldatum();
            risiko = new InakzeptablesRisiko();
            this.risiko = (InakzeptablesRisiko) risiko;
            this.risiko.setBezeichnung(bezeichnung);
            this.risiko.setEintrittswahrscheinlichkeit(eintrittswahrscheinlichkeit);
            this.risiko.setKosten_im_schadensfall(kostenimschadensfall);
            this.risiko.setErstellungsdatum(erstelldatum);
        }else if(risiko.getClass().equals(InakzeptablesRisiko.class)) {
            this.risiko = (InakzeptablesRisiko)  risiko;
        }else {
            System.out.println("irgendwas ist kaputt");
        }
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    public void showView() {
        this.setScene(createScene());
        this.setTitle("RisikoInakzeptabelErfassungView");
        this.show();
    }

    private void initProperties() {
        massnahmen = new SimpleStringProperty();
    }

    private GridPane createGrid() {
        TextField tf_massnahmen = new TextField();

        massnahmen.bind(tf_massnahmen.textProperty());
        tf_massnahmen.textProperty().set(risiko.getMassnahme());


        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Bezeichnung:"), 0, 0);
        gridPane.add(new Label(risiko.getBezeichnung()), 1, 0);

        gridPane.add(new Label("Maßnahmen:"), 0, 1);
        gridPane.add(tf_massnahmen, 1, 1);

        return gridPane;
    }

    private HBox createButtons() {
        Button weiterButton = new Button("Weiter");
        Button abbrechenButton = new Button("Abbrechen");

        weiterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                risiko.setMassnahme(massnahmen.get());
                try {
                    risiko.druckeDaten(System.out);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        HBox buttons = new HBox(weiterButton, abbrechenButton);
        buttons.setAlignment(Pos.CENTER);
        return buttons;
    }

    private Scene createScene() {
        initProperties();
        VBox vbox = new VBox(createGrid(), createButtons());
        vbox.setPadding(new Insets(30));
        return new Scene(vbox, Color.GRAY);
    }

}
