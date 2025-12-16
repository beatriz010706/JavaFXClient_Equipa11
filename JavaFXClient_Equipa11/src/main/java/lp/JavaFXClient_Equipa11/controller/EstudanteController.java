package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class EstudanteController {

    @FXML private TextField txtEstudanteId;
    @FXML private TextField txtNumeroAluno;
    @FXML private TextField txtCurso;
    @FXML private TextField txtProgramaId;

    private final ApiService api = new ApiService();

    @FXML
    public void consultarHistorico() {
        String estudanteId = txtEstudanteId.getText();
        if (estudanteId.isBlank()) { alert("Indique o ID do estudante."); return; }
        api.get("/estudantes/" + estudanteId + "/historico");
        alert("Histórico consultado com sucesso.");
    }

    @FXML
    public void candidatar() {
        String estudanteId = txtEstudanteId.getText();
        String programaId = txtProgramaId.getText();
        if (estudanteId.isBlank() || programaId.isBlank()) { alert("Indique ID do estudante e programa."); return; }
        String json = """
                {"estudanteId": %s,"programaId": %s}
                """.formatted(estudanteId, programaId);
        api.post("/candidaturas", json);
        alert("Candidatura submetida com sucesso.");
    }

    @FXML
    public void verEstado() {
        String estudanteId = txtEstudanteId.getText();
        if (estudanteId.isBlank()) { alert("Indique o ID do estudante."); return; }
        api.get("/estudantes/" + estudanteId + "/estado");
        alert("Estado da candidatura consultado.");
    }

    private void alert(String msg) { new Alert(Alert.AlertType.INFORMATION, msg).show(); }
}
