package lp.JavaFXClient_Equipa11.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ProgEstudanteController {

    @FXML private TextField txtProgramaEstudanteId;
    @FXML private TextField txtHoras;
    @FXML private ProgressBar progressBar;

    private final ApiService api = new ApiService();

    @FXML
    public void adicionarHoras() {
        String id = txtProgramaEstudanteId.getText();
        String horas = txtHoras.getText();
        if (id.isBlank() || horas.isBlank()) { alert("Indique ID e horas."); return; }
        api.post("/programas-estudante/" + id + "/horas?valor=" + horas, "");
        alert("Horas adicionadas com sucesso.");
    }

    @FXML
    public void concluirPrograma() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique ID do programa do estudante."); return; }
        api.post("/programas-estudante/" + id + "/concluir", "");
        alert("Programa concluído.");
    }

    @FXML
    public void emitirDiploma() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique ID do programa do estudante."); return; }
        api.post("/programas-estudante/" + id + "/diploma", "");
        alert("Diploma emitido.");
    }

    @FXML
    public void getProgresso() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique ID do programa do estudante."); return; }
        api.get("/programas-estudante/" + id + "/progresso");
        alert("Progresso consultado.");
    }

    @FXML
    public void cancelarParticipacao() {
        String id = txtProgramaEstudanteId.getText();
        if (id.isBlank()) { alert("Indique ID do programa do estudante."); return; }
        api.post("/programas-estudante/" + id + "/cancelar", "");
        alert("Participação cancelada.");
    }

    private void alert(String msg) { new Alert(Alert.AlertType.INFORMATION, msg).show(); }
}
