package fb.fb4.pk1jfx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RisikoErfassungView extends Stage {
    public RisikoErfassungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);

    }

    public void showView() {
        Label root = new Label("Risikoerfassung");
        Scene scene = new Scene(root, Color.GRAY);
        this.setScene(scene);
        this.setTitle("Risikoerfassung");
        this.show();
    }
}
