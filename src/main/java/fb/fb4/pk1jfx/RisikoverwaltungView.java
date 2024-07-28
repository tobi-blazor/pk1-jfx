package fb.fb4.pk1jfx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RisikoverwaltungView extends Stage {
    public RisikoverwaltungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, Color.GRAY);
        stage.setScene(scene);
        stage.setTitle("Risikoverwaltung");
    }
}
