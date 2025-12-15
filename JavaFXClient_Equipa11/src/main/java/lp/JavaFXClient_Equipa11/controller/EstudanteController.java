package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */


import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class EstudanteController {

    @FXML private TextField txtEstudanteId, txtProgramaId;
    @FXML private TableView<?> historicoTable;

    private final ApiService api = new ApiService();

    @FXML
    public void consultarHistorico() {
        String estudanteId = txtEstudanteId.getText();
        if (estudanteId == null || estudanteId.isBlank()) {
            alert("Informe o ID do estudante.");
            return;
        }
        api.get("/estudantes/" + estudanteId + "/historico");
        alert("Histórico consultado com sucesso!");
    }

    @FXML
    public void candidatar() {
        String estudanteId = txtEstudanteId.getText();
        String programaId = txtProgramaId.getText();

        if (estudanteId.isBlank() || programaId.isBlank()) {
            alert("Informe o ID do estudante e do programa.");
            return;
        }

        String json = """
        {"estudanteId":%s,"programaId":%s}
        """.formatted(estudanteId, programaId);

        api.post("/candidaturas/criar", json);
        alert("Candidatura submetida com sucesso!");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
