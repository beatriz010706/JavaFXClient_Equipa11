package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */


import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.modelDTO.TipoPrograma;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ParceiroController {

    @FXML private TextField txtParceiroId;
    @FXML private ComboBox<TipoPrograma> tipoPrograma;
    @FXML private TableView<?> programasTable;

    private final ApiService api = new ApiService();

    @FXML
    public void initialize() {
        tipoPrograma.getItems().setAll(TipoPrograma.values());
    }

    @FXML
    public void registarPrograma() {
        String parceiroId = txtParceiroId.getText();
        TipoPrograma tipo = tipoPrograma.getValue();

        if (parceiroId.isBlank()) {
            alert("Informe o ID do parceiro.");
            return;
        }
        if (tipo == null) {
            alert("Selecione o tipo de programa.");
            return;
        }

        String json = """
        {"parceiroId": %s, "tipoPrograma": "%s"}
        """.formatted(parceiroId, tipo.name());

        api.post("/programas/criar", json);
        alert("Programa registado com sucesso!");
    }

    @FXML public void editarPrograma() { alert("Editar Programa ainda não implementado."); }
    @FXML public void eliminarPrograma() { alert("Eliminar Programa ainda não implementado."); }

    @FXML
    public void aprovarCandidatura() {
        api.post("/candidaturas/1/aprovar", "");
        alert("Candidatura aprovada!");
    }

    @FXML
    public void rejeitarCandidatura() {
        api.post("/candidaturas/1/rejeitar", "");
        alert("Candidatura rejeitada!");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
