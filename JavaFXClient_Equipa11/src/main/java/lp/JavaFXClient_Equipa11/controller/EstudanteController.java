package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */


import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

/**
 * Controller para funcionalidades do estudante.
 * NÃO lida com registo/login.
 */
public class EstudanteController {

    @FXML private TextField txtEstudanteId;
    @FXML private TextField txtProgramaId;

    private final ApiService api = new ApiService();

    /** Consultar histórico de programas do estudante */
    @FXML
    public void consultarHistorico() {
        String estudanteId = txtEstudanteId.getText();
        if (estudanteId.isBlank()) {
            alert("Indique o ID do estudante.");
            return;
        }
        String resposta = api.get("/estudantes/" + estudanteId + "/historico");
        alert("Histórico consultado:\n" + resposta);
    }

    /** Candidatar-se a um programa */
    @FXML
    public void candidatar() {
        String estudanteId = txtEstudanteId.getText();
        String programaId = txtProgramaId.getText();
        if (estudanteId.isBlank() || programaId.isBlank()) {
            alert("Indique ID do estudante e do programa.");
            return;
        }

        String json = """
                {"estudanteId": %s,"programaId": %s}
                """.formatted(estudanteId, programaId);

        api.post("/candidaturas", json);
        alert("Candidatura submetida com sucesso.");
    }

    /** Consultar estado de candidaturas */
    @FXML
    public void verEstado() {
        String estudanteId = txtEstudanteId.getText();
        if (estudanteId.isBlank()) {
            alert("Indique o ID do estudante.");
            return;
        }
        String resposta = api.get("/estudantes/" + estudanteId + "/estado");
        alert("Estado da candidatura:\n" + resposta);
    }

    /** Método auxiliar para exibir alertas */
    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
