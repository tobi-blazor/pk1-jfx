module fb.fb4.pk1jfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fb.fb4.pk1jfx to javafx.fxml;
    exports fb.fb4.pk1jfx;
    exports fh.fb4.gui;
    opens fh.fb4.gui to javafx.fxml;
    exports fh.fb4.fachlogik;
    opens fh.fb4.fachlogik to javafx.fxml;
}