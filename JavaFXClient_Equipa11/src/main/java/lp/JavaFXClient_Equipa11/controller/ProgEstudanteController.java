package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ProgEstudanteController {

    @FXML private TextField txtHoras;
    @FXML private TableView<?> programaEstudanteTable;

    private final ApiService api = new ApiService();

    @FXML
    public void adicionarHoras() {
        String horas = txtHoras.getText();
        if (horas.isBlank()) { alert("Informe as horas a adicionar."); return; }
        api.post("/programa-estudante/adicionar-horas", "{\"horas\":" + horas + "}");
        alert("Horas adicionadas com sucesso!");
    }

    @FXML
    public void concluirPrograma() {
        api.post("/programa-estudante/concluir", "");
        alert("Programa concluído!");
    }

    @FXML
    public void emitirDiploma() {
        api.post("/programa-estudante/diploma", "");
        alert("Diploma emitido!");
    }

    @FXML
    public void getProgresso() {
        api.get("/programa-estudante/progresso");
        alert("Progresso consultado!");
    }

    @FXML
    public void cancelarParticipacao() {
        api.post("/programa-estudante/cancelar", "");
        alert("Participação cancelada!");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}

