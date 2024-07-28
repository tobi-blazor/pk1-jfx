package fb.fb4.pk1jfx;

import javafx.beans.property.SimpleStringProperty;
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

public class RisikoErfassungView extends Stage {
    Risikoverwaltung risikoverwaltung;
    SimpleStringProperty bezeichnung, eintrittswahrscheinlichkeit, kostenImSchadensfall;

    public RisikoErfassungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        this.risikoverwaltung = risikoverwaltung;
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
    }

    private GridPane createGrid() {
        TextField tf_Bezeichnung = new TextField();
        TextField tf_Eintrittswahrscheinlichkeit = new TextField();
        TextField tf_KostenImSchadensfall = new TextField();

        bezeichnung.bind(tf_Bezeichnung.textProperty());
        eintrittswahrscheinlichkeit.bind(tf_Eintrittswahrscheinlichkeit.textProperty());
        kostenImSchadensfall.bind(tf_KostenImSchadensfall.textProperty());

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Bezeichnung:"), 0, 0);
        gridPane.add(tf_Bezeichnung, 1, 0);

        gridPane.add(new Label("Eintrittswahrscheinlichkeit:"), 0, 1);
        gridPane.add(tf_Eintrittswahrscheinlichkeit, 1, 1);

        gridPane.add(new Label("Kosten im Schadensfall:"), 0, 2);
        gridPane.add(tf_KostenImSchadensfall, 1, 2);
        return gridPane;
    }

    private HBox createButtons() {
        Button weiterButton = new Button("Weiter");
        Button abbrechenButton = new Button("Abbrechen");

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
