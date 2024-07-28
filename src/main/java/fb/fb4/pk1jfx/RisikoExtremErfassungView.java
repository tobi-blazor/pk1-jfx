package fb.fb4.pk1jfx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RisikoExtremErfassungView extends Stage {
    public RisikoExtremErfassungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, Color.GRAY);
        this.setScene(scene);
        this.setTitle("RisikoExtremErfassungView");
    }

    public void showView() {
        Label root = new Label("RisikoExtremErfassungView");
        Scene scene = new Scene(root, Color.GRAY);
        this.setScene(scene);
        this.setTitle("RisikoExtremErfassungView");
        this.show();
    }
}
