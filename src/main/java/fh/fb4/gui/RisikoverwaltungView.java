package fh.fb4.gui;

import fh.fb4.fachlogik.Risikoverwaltung;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class RisikoverwaltungView extends Stage {
    Stage stage;

    public RisikoverwaltungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        this.stage = stage;

    }

    private MenuBar generateMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu m_Datei = new Menu("Datei");
        MenuItem mi_Laden = new MenuItem("Laden");
        MenuItem mi_Speichern = new MenuItem("Speichern");
        MenuItem mi_ListeInDatei = new MenuItem("Risikoliste in Datei");
        MenuItem mi_Beenden = new MenuItem("Beenden");
        m_Datei.getItems().addAll(mi_Laden, mi_Speichern, new SeparatorMenuItem(), mi_ListeInDatei, new SeparatorMenuItem(), mi_Beenden);

        Menu m_Risiko = new Menu("Risiko");
        MenuItem mi_NeuesRisiko = new MenuItem("Neues Risiko");
        m_Risiko.getItems().addAll(mi_NeuesRisiko);

        Menu m_Anzeige = new Menu("Anzeige");
        MenuItem mi_RisikoMaxRueck = new MenuItem("Risiko mit maximaler Rückstellung");
        MenuItem mi_SumAllRueck = new MenuItem("Summe aller Rückstellungen");
        m_Anzeige.getItems().addAll(mi_RisikoMaxRueck, mi_SumAllRueck);

        menuBar.getMenus().addAll(m_Datei, m_Risiko, m_Anzeige);
        return menuBar;
    }
    private BorderPane generateBorderPane() {

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(generateMenuBar());
        return borderPane;
    }

    public void showView() {
        stage.setScene(new Scene(generateBorderPane()));
        stage.setTitle("Risikoverwaltung");
        stage.show();
    }
}
