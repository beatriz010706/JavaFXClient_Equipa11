package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.modelDTO.TipoPrograma;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ParceiroController {

    @FXML private TextField txtParceiroId;
    @FXML private ComboBox<TipoPrograma> tipoPrograma;
    @FXML private TableView<?> candidaturasTable;

    private final ApiService api = new ApiService();

    @FXML
    public void initialize() {
        tipoPrograma.getItems().setAll(TipoPrograma.values());
    }

    @FXML public void registarPrograma() { }
    @FXML public void editarPrograma() { }
    @FXML public void eliminarPrograma() { }

    @FXML
    public void aprovarCandidatura() {
        api.post("/candidaturas/1/aprovar", "");
    }

    @FXML
    public void rejeitarCandidatura() {
        api.post("/candidaturas/1/rejeitar", "");
    }
}
