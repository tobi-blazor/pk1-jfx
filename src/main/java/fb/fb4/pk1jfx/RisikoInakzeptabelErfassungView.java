package fb.fb4.pk1jfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RisikoInakzeptabelErfassungView extends Stage {
    Risikoverwaltung risikoverwaltung;
    AkzeptablesRisiko akzeptablesRisiko;
    SimpleStringProperty massnahmen;
    public RisikoInakzeptabelErfassungView(Risikoverwaltung risikoverwaltung, Stage stage, AkzeptablesRisiko risiko) {
        this.risikoverwaltung = risikoverwaltung;
        this.akzeptablesRisiko = risiko;
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


        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Bezeichnung:"), 0, 0);
        gridPane.add(new Label(akzeptablesRisiko.getBezeichnung()), 1, 0);

        gridPane.add(new Label("Maßnahmen:"), 0, 1);
        gridPane.add(tf_massnahmen, 1, 1);

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
