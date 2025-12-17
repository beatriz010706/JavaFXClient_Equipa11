package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class UtilizadorController {

    @FXML private ComboBox<String> tipoUtilizador;
    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtCurso;
    @FXML private TextField txtNumeroAluno;
    @FXML private ComboBox<String> tipoParceiro;

    private final ApiService api = new ApiService();

    @FXML
    public void initialize() {
        // Apenas os tipos concretos
        tipoUtilizador.getItems().setAll("ESTUDANTE", "PARCEIRO");
        tipoUtilizador.getSelectionModel().selectFirst();

        // Preencher comboBox do tipo de parceiro
        tipoParceiro.getItems().setAll("EMPRESA", "ONG", "INSTITUICAO_PUBLICA");

        // Atualiza campos ao mudar tipo
        tipoUtilizador.setOnAction(e -> atualizarCampos());
        atualizarCampos();
    }

    /** Habilita/desabilita campos conforme tipo */
    private void atualizarCampos() {
        boolean isEstudante = tipoUtilizador.getValue().equals("ESTUDANTE");
        txtCurso.setDisable(!isEstudante);
        txtNumeroAluno.setDisable(!isEstudante);
        tipoParceiro.setDisable(isEstudante);
    }

    /** Registo do utilizador */
    @FXML
    public void registar() {
        String endpoint;
        String json;

        if (tipoUtilizador.getValue().equals("ESTUDANTE")) {
            // Validação
            if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() ||
                txtPassword.getText().isBlank() || txtCurso.getText().isBlank() ||
                txtNumeroAluno.getText().isBlank()) {
                alert("Preencha todos os campos do estudante.");
                return;
            }

            int numeroAluno;
            try {
                numeroAluno = Integer.parseInt(txtNumeroAluno.getText());
            } catch (NumberFormatException e) {
                alert("Número de aluno inválido.");
                return;
            }

            endpoint = "/estudantes/registar";
            json = """
                {
                  "nome":"%s",
                  "email":"%s",
                  "password":"%s",
                  "curso":"%s",
                  "numeroAluno":%d
                }
                """.formatted(txtNome.getText(), txtEmail.getText(),
                               txtPassword.getText(), txtCurso.getText(),
                               numeroAluno);

        } else { // PARCEIRO
            if (txtNome.getText().isBlank() || txtEmail.getText().isBlank() ||
                txtPassword.getText().isBlank() || tipoParceiro.getValue() == null) {
                alert("Preencha todos os campos do parceiro.");
                return;
            }

            endpoint = "/parceiros/registar";
            json = """
                {
                  "nome":"%s",
                  "email":"%s",
                  "password":"%s",
                  "tipo":"%s"
                }
                """.formatted(txtNome.getText(), txtEmail.getText(),
                               txtPassword.getText(), tipoParceiro.getValue());
        }

        api.post(endpoint, json);
        alert("Registo efetuado com sucesso!");
    }

    /** Login do utilizador */
    @FXML
    public void login() {
        if (txtEmail.getText().isBlank() || txtPassword.getText().isBlank()) {
            alert("Email e password são obrigatórios.");
            return;
        }

        String endpoint = tipoUtilizador.getValue().equals("ESTUDANTE") ?
                "/estudantes/login" : "/parceiros/login";

        String json = """
                {
                  "email":"%s",
                  "senha":"%s"
                }
                """.formatted(txtEmail.getText(), txtPassword.getText());

        api.post(endpoint, json);
        alert("Login efetuado.");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}//fim classe
