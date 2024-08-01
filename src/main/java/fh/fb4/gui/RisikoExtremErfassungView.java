package fh.fb4.gui;

import fh.fb4.fachlogik.ExtremesRisiko;
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

public class RisikoExtremErfassungView extends Stage {
    ExtremesRisiko risiko;
    SimpleStringProperty massnahmen, versicherungsbeitrag;
    Label errorMessage;

    public RisikoExtremErfassungView(ExtremesRisiko risiko, Stage stage) {
        this.risiko = risiko;
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    public void showView() {
        this.setScene(createScene());
        this.setTitle("RisikoExtremErfassungView");
        this.show();
    }

    private void initProperties() {
        massnahmen = new SimpleStringProperty();
        versicherungsbeitrag = new SimpleStringProperty();
        errorMessage = new Label("");
    }

    private GridPane createGrid() {
        TextField tf_massnahmen = new TextField();
        TextField tf_versicherungsbeitrag = new TextField();

        massnahmen.bind(tf_massnahmen.textProperty());
        tf_massnahmen.textProperty().set(risiko.getMassnahme());
        versicherungsbeitrag.bind(tf_versicherungsbeitrag.textProperty());
        tf_versicherungsbeitrag.textProperty().set(Float.toString(risiko.getVersicherungsbeitrag()));


        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Bezeichnung:"), 0, 0);
        gridPane.add(new Label(risiko.getBezeichnung()), 1, 0);

        gridPane.add(new Label("Maßnahmen:"), 0, 1);
        gridPane.add(tf_massnahmen, 1, 1);

        gridPane.add(new Label("Versicherungsbeitrag:"), 0, 2);
        gridPane.add(tf_versicherungsbeitrag, 1, 2);

        gridPane.add(new Label("Fehler:"), 0, 3);
        gridPane.add(errorMessage, 1, 3);

        return gridPane;
    }

    private HBox createButtons() {
        Button weiterButton = new Button("Weiter");
        Button abbrechenButton = new Button("Abbrechen");

        weiterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    risiko.setVersicherungsbeitrag(Float.parseFloat(versicherungsbeitrag.get()));
                }catch (NumberFormatException numberFormatException) {
                    errorMessage.textProperty().set(numberFormatException.toString());
                }
                risiko.setMassnahme(massnahmen.get());
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
