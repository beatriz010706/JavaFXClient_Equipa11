package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ParceiroController {

    @FXML private TextField txtParceiroId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtHoras;
    @FXML private TextField txtLocal;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtVagas;

    @FXML private TextField txtCandidaturaId;

    private final ApiService api = new ApiService();

    //  REGISTAR PROGRAMA 
    @FXML
    private void registarPrograma() {

        String json = """
        {
          "titulo": "%s",
          "horas_servico": %s,
          "local": "%s",
          "descricao": "%s",
          "vagas": %s
        }
        """.formatted(
                txtTitulo.getText(),
                txtHoras.getText(),
                txtLocal.getText(),
                txtDescricao.getText(),
                txtVagas.getText()
        );

        api.post("/parceiros/" + txtParceiroId.getText() + "/programa/registar", json);
        alerta("Programa registado!");
    }

    //  APROVAR CANDIDATURA 
    @FXML
    private void aprovarCandidatura() {
        api.post("/candidaturas/" + txtCandidaturaId.getText() + "/aprovar", "");
        alerta("Candidatura aprovada!");
    }

    // REJEITAR CANDIDATURA 
    @FXML
    private void rejeitarCandidatura() {
        api.post("/candidaturas/" + txtCandidaturaId.getText() + "/rejeitar", "");
        alerta("Candidatura rejeitada!");
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
