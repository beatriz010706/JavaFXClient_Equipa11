package lp.JavaFXClient_Equipa11.controller;
/**
 * @author gonçalo silva
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class CandidaturaController {

    @FXML private TextField txtCandidaturaId;
    @FXML private TextField txtProgramaId;
    @FXML private TextField txtEstudanteId;

    private final ApiService api = new ApiService();

    @FXML
    private void listarPorEstudante() {
        String id = txtEstudanteId.getText();
        String resposta = api.get("/candidaturas/estudante/" + id);
        alerta(resposta);
    }

    @FXML
    private void listarPorPrograma() {
        String id = txtProgramaId.getText();
        String resposta = api.get("/candidaturas/programa/" + id);
        alerta(resposta);
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}

