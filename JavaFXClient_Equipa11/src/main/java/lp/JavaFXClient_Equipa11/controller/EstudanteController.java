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
        api.get("/estudantes/" + txtEstudanteId.getText() + "/historico");
    }

    @FXML
    public void candidatar() {
        String json = """
        {"estudanteId":%s,"programaId":%s}
        """.formatted(txtEstudanteId.getText(), txtProgramaId.getText());

        api.post("/candidaturas/criar", json);
        alert("Candidatura submetida");
    }

    private void alert(String m) {
        new Alert(Alert.AlertType.INFORMATION, m).show();
    }
}
