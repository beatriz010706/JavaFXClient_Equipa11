package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class EstudanteController {

    @FXML
    private TextField txtEstudanteId;

    @FXML
    private TextField txtProgramaId;

    private final ApiService api = new ApiService();

    /* ===================== CONSULTAR HISTÓRICO ===================== */

    @FXML
    public void consultarHistorico() {
        String estudanteId = txtEstudanteId.getText();

        if (estudanteId == null || estudanteId.isBlank()) {
            alert("Indique o ID do estudante.");
            return;
        }

        api.get("/estudantes/" + estudanteId + "/historico");
        alert("Histórico consultado com sucesso.");
    }

    /* ===================== CANDIDATAR ===================== */

    @FXML
    public void candidatar() {
        String estudanteId = txtEstudanteId.getText();
        String programaId = txtProgramaId.getText();

        if (estudanteId.isBlank() || programaId.isBlank()) {
            alert("Indique o ID do estudante e do programa.");
            return;
        }

        String json = """
        {
          "estudanteId": %s,
          "programaId": %s
        }
        """.formatted(estudanteId, programaId);

        api.post("/candidaturas", json);
        alert("Candidatura submetida com sucesso.");
    }

    /* ===================== ALERTA ===================== */

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
