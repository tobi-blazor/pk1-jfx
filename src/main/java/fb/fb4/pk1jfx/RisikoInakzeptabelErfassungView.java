package fb.fb4.pk1jfx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RisikoInakzeptabelErfassungView extends Stage {
    public RisikoInakzeptabelErfassungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, Color.GRAY);
        this.setScene(scene);
        this.setTitle("RisikoInakzeptabelErfassungView");
    }

    public void showView() {
        Label root = new Label("RisikoInakzeptabelErfassungView");
        Scene scene = new Scene(root, Color.GRAY);
        this.setScene(scene);
        this.setTitle("RisikoInakzeptabelErfassungView");
        this.show();
    }
}
