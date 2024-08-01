package fh.fb4.gui;

import fh.fb4.datenhaltung.IDao;
import fh.fb4.datenhaltung.PersistenzException;
import fh.fb4.datenhaltung.SerialisierungDao;
import fh.fb4.fachlogik.AkzeptablesRisiko;
import fh.fb4.fachlogik.Risiko;
import fh.fb4.fachlogik.Risikoverwaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class RisikoverwaltungView extends Stage {
    Stage stage;
    IDao dao;
    Risikoverwaltung risikoverwaltung;

    public RisikoverwaltungView(Risikoverwaltung risikoverwaltung, Stage stage) {
        this.risikoverwaltung = risikoverwaltung;
        this.stage = stage;
        dao = new SerialisierungDao();
    }

    public class Laden implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                dao.laden();
            } catch (PersistenzException ex) {
                System.out.println("Fehler beim laden");
            }
        }
    }

    public class Speichern implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                dao.speichern(risikoverwaltung.risikoList);
            } catch (PersistenzException ex) {
                System.out.println("Fehler beim speichern");
            }
        }
    }

    public class RisikolisteInDatei implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                risikoverwaltung.druckeRisikenInDatei("RisikoInDatei.txt");
            } catch (IOException ex) {
                throw new RuntimeException("Fehler bei in Datei schreiben");
            }
        }
    }

    public class Beenden implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            stage.close();
        }
    }

    public class NeuesRisiko implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {

            Risiko risiko = new AkzeptablesRisiko();
            risikoverwaltung.risikoList.add(risiko);
            RisikoErfassungView risikoErfassungView = new RisikoErfassungView(risiko, stage);
            risikoErfassungView.showView();
        }
    }


    private MenuBar generateMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu m_Datei = new Menu("Datei");

        MenuItem mi_Laden = new MenuItem("Laden");
        mi_Laden.setOnAction(new Laden());

        MenuItem mi_Speichern = new MenuItem("Speichern");
        mi_Speichern.setOnAction(new Speichern());

        MenuItem mi_ListeInDatei = new MenuItem("Risikoliste in Datei");
        mi_ListeInDatei.setOnAction(new RisikolisteInDatei());

        MenuItem mi_Beenden = new MenuItem("Beenden");
        mi_Beenden.setOnAction(new Beenden());

        m_Datei.getItems().addAll(mi_Laden, mi_Speichern, new SeparatorMenuItem(), mi_ListeInDatei, new SeparatorMenuItem(), mi_Beenden);


        Menu m_Risiko = new Menu("Risiko");

        MenuItem mi_NeuesRisiko = new MenuItem("Neues Risiko");
        mi_NeuesRisiko.setOnAction(new NeuesRisiko());

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
