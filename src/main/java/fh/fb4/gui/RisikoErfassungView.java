package fh.fb4.gui;

import fh.fb4.fachlogik.ExtremesRisiko;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;


public class RisikoErfassungView extends Stage {
    Risiko risiko;
    SimpleStringProperty bezeichnung, eintrittswahrscheinlichkeit, kostenImSchadensfall;
    Label errorMessage;
    private final float LIMIT = 10000f;
    private final float KOSTENLIMIT = 1000000f;
    Stage stage;

    public RisikoErfassungView(Risiko risiko, Stage stage) {
        this.risiko = risiko;
        this.stage = stage;
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    public void showView() {
        this.setScene(createScene());
        this.setTitle("Risikoerfassung");
        this.show();
    }

    private void initProperties() {
        bezeichnung = new SimpleStringProperty();
        eintrittswahrscheinlichkeit = new SimpleStringProperty();
        kostenImSchadensfall = new SimpleStringProperty();
        errorMessage = new Label("");
    }

    private GridPane createGrid() {
        TextField tf_Bezeichnung = new TextField();
        TextField tf_Eintrittswahrscheinlichkeit = new TextField();
        TextField tf_KostenImSchadensfall = new TextField();

        bezeichnung.bind(tf_Bezeichnung.textProperty());
        tf_Bezeichnung.textProperty().set(risiko.getBezeichnung());
        eintrittswahrscheinlichkeit.bind(tf_Eintrittswahrscheinlichkeit.textProperty());
        tf_Eintrittswahrscheinlichkeit.textProperty().setValue(Float.toString(risiko.getEintrittswahrscheinlichkeit()));
        kostenImSchadensfall.bind(tf_KostenImSchadensfall.textProperty());
        tf_KostenImSchadensfall.textProperty().setValue(Float.toString(risiko.getKosten_im_schadensfall()));

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Bezeichnung:"), 0, 0);
        gridPane.add(tf_Bezeichnung, 1, 0);

        gridPane.add(new Label("Eintrittswahrscheinlichkeit:"), 0, 1);
        gridPane.add(tf_Eintrittswahrscheinlichkeit, 1, 1);

        gridPane.add(new Label("Kosten im Schadensfall:"), 0, 2);
        gridPane.add(tf_KostenImSchadensfall, 1, 2);


        gridPane.add(new Label("Fehler:"), 0, 3);
        gridPane.add(errorMessage, 1, 3);


        return gridPane;
    }

    private HBox createButtons() {
        Button weiterButton = new Button("Weiter");
        Button abbrechenButton = new Button("Abbrechen");
        abbrechenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("schließen owo");
            }
        });

        weiterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    risiko.setEintrittswahrscheinlichkeit(Float.parseFloat(eintrittswahrscheinlichkeit.get()));
                    risiko.setKosten_im_schadensfall(Float.parseFloat(kostenImSchadensfall.get()));
                }catch (NumberFormatException numberFormatException) {
                    errorMessage.textProperty().set(numberFormatException.toString());
                }
                risiko.setBezeichnung(bezeichnung.get());
                risiko.setErstellungsdatum(LocalDate.now());
                if(isInakzeptablesRisiko()) {
                    RisikoInakzeptabelErfassungView risikoInakzeptabelErfassungView = new RisikoInakzeptabelErfassungView(risiko, stage);
                    risikoInakzeptabelErfassungView.showView();
                    System.out.println(risiko.toString());
                }else if (isExtremesRisiko()) {
                    RisikoExtremErfassungView risikoExtremErfassungView = new RisikoExtremErfassungView(risiko, stage);
                    risikoExtremErfassungView.showView();
                }
            }
        });

        HBox buttons = new HBox(weiterButton, abbrechenButton);
        buttons.setAlignment(Pos.CENTER);
        return buttons;
    }
    private boolean isAkzeptablesRisiko() {
        float risikowert = risiko.getEintrittswahrscheinlichkeit() * risiko.getKosten_im_schadensfall();
        return risikowert < LIMIT;
    }

    private boolean isInakzeptablesRisiko() {
        float risikowert = risiko.getEintrittswahrscheinlichkeit() * risiko.getKosten_im_schadensfall();
        return risikowert < KOSTENLIMIT && risikowert >= LIMIT;
    }

    private boolean isExtremesRisiko() {
        float risikowert = risiko.getEintrittswahrscheinlichkeit() * risiko.getKosten_im_schadensfall();
        return risikowert>= KOSTENLIMIT;
    }


    private Scene createScene() {
        initProperties();
        VBox vbox = new VBox(createGrid(), createButtons());
        vbox.setPadding(new Insets(30));
        return new Scene(vbox, Color.GRAY);
    }
}
