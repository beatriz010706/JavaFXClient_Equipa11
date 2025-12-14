package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class EstudanteController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private TextField txtCurso;
    @FXML private TextField txtNumeroAluno;

    @FXML private TextField txtEstudanteId;
    @FXML private TextField txtProgramaId;

    private final ApiService api = new ApiService();

    //  REGISTAR ESTUDANTE 
    @FXML
    private void registarEstudante() {

        String json = """
        {
          "nome": "%s",
          "email": "%s",
          "password": "%s",
          "curso": "%s",
          "numeroAluno": %s
        }
        """.formatted(
                txtNome.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                txtCurso.getText(),
                txtNumeroAluno.getText()
        );

        api.post("/estudantes/registar", json);
        alerta("Estudante registado com sucesso!");
    }

    //  LOGIN ESTUDANTE 
    @FXML
    private void loginEstudante() {

        String json = """
        {
          "email": "%s",
          "senha": "%s"
        }
        """.formatted(txtEmail.getText(), txtPassword.getText());

        api.post("/estudantes/login", json);
        alerta("Login efetuado!");
    }

    //  CONSULTAR HISTÓRICO 
    @FXML
    private void consultarHistorico() {
        String id = txtEstudanteId.getText();
        String resposta = api.get("/estudantes/" + id + "/historico");
        alerta(resposta);
    }

    // CANDIDATAR A PROGRAMA 
    @FXML
    private void candidatarPrograma() {

        String json = """
        {
          "estudanteId": %s,
          "programaId": %s
        }
        """.formatted(txtEstudanteId.getText(), txtProgramaId.getText());

        api.post("/candidaturas/criar", json);
        alerta("Candidatura submetida!");
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
