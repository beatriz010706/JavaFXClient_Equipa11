package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ProgEstudanteController {

    @FXML private TextField txtProgramaEstudanteId;
    @FXML private TextField txtHoras;

    private ApiService api;

    @FXML
    public void initialize() {
        api = new ApiService();
    }

    @FXML
    private void adicionarHoras() {
        api.post("/programasEstudante/" + txtProgramaEstudanteId.getText()
                + "/adicionarHoras?horas=" + txtHoras.getText(), "");
        alerta("Horas adicionadas!");
    }

    @FXML
    private void consultarProgresso() {
        String res = api.get("/programasEstudante/" + txtProgramaEstudanteId.getText() + "/progresso");
        alerta(res);
    }

    @FXML
    private void emitirDiploma() {
        String res = api.get("/programasEstudante/" + txtProgramaEstudanteId.getText() + "/emitirDiploma");
        alerta(res);
    }

    @FXML
    private void cancelarParticipacao() {
        api.post("/programasEstudante/" + txtProgramaEstudanteId.getText() + "/cancelar", "");
        alerta("Participação cancelada!");
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
