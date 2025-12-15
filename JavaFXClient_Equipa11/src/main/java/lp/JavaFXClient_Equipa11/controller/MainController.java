package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MainController {
    @FXML private AnchorPane contentPane;

    @FXML
    public void initialize() {
        abrirMenuEstudante();
    }

    @FXML public void abrirMenuEstudante() { loadView("/estudante-view.fxml"); }
    @FXML public void abrirMenuParceiro()   { loadView("/parceiro-view.fxml"); }
    @FXML public void abrirMenuProgramas()  { loadView("/programa-view.fxml"); }
    @FXML public void abrirMenuCandidaturas(){ loadView("/candidaturas-view.fxml"); }
    @FXML public void abrirMenuProgramaEstudante(){ loadView("/prog-estudante-view.fxml"); }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            contentPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao carregar vista: " + e.getMessage()).showAndWait();
        }
    }
}//fim classe