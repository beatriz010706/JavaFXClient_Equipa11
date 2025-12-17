package lp.JavaFXClient_Equipa11.controller;
/**
 * @author equipa 11
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ProgEstudanteController {

    @FXML private TextField txtProgramaEstudanteId;
    @FXML private TextField txtHoras;

    private final ApiService api = new ApiService();

    @FXML
    public void adicionarHoras() {
        if (txtProgramaEstudanteId.getText().isBlank() || txtHoras.getText().isBlank()) {
            alert("Preencha todos os campos.");
            return;
        }
        String json = """
                {"horas": %s}
                """.formatted(txtHoras.getText());
        api.post("/programaEstudante/" + txtProgramaEstudanteId.getText() + "/adicionarHoras", json);
        alert("Horas adicionadas com sucesso.");
    }

    @FXML
    public void getProgresso() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique o ID do programa."); return; }
        String resposta = api.get("/programaEstudante/" + id + "/progresso");
        alert("Progresso: " + resposta);
    }

    @FXML
    public void concluirPrograma() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique o ID do programa."); return; }
        try {
            api.post("/programaEstudante/" + id + "/concluir", "");
            alert("Programa concluído.");
        } catch (Exception e) {
            alert("Erro: " + e.getMessage());
        }

    }

    @FXML
    public void emitirDiploma() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique o ID do programa."); return; }
        api.post("/programaEstudante/" + id + "/emitirDiploma", "");
        alert("Diploma emitido.");
    }

    @FXML
    public void cancelarParticipacao() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique o ID do programa."); return; }
        api.post("/programaEstudante/" + id + "/cancelar", "");
        alert("Participação cancelada.");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}//fim classe
