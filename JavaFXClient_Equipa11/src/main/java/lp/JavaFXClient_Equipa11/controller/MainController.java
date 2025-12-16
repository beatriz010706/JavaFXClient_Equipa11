package lp.JavaFXClient_Equipa11.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML private BorderPane mainPane;

    @FXML
    public void initialize() {
        abrirMenuUtilizador();
    }

    @FXML private void abrirMenuUtilizador() { loadView("/utilizador-view.fxml"); }
    @FXML private void abrirMenuEstudante() { loadView("/estudante-view.fxml"); }
    @FXML private void abrirMenuParceiro() { loadView("/parceiro-view.fxml"); }
    @FXML private void abrirMenuProgramas() { loadView("/programa-view.fxml"); }
    @FXML private void abrirMenuCandidaturas() { loadView("/candidaturas-view.fxml"); }
    @FXML private void abrirMenuProgramaEstudante() { loadView("/prog-estudante-view.fxml"); }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();
            mainPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
