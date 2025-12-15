package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class UtilizadorController {

    @FXML private ComboBox<String> tipoUtilizador;
    @FXML private TextField txtNome, txtEmail;
    @FXML private PasswordField txtPassword;

    private final ApiService api = new ApiService();

    @FXML
    public void initialize() {
        tipoUtilizador.getItems().addAll("ESTUDANTE", "PARCEIRO");
        tipoUtilizador.getSelectionModel().selectFirst();
    }

    @FXML
    public void registar() {
        String endpoint = tipoUtilizador.getValue().equals("ESTUDANTE")
                ? "/estudantes/registar"
                : "/parceiros/registar";

        String json = """
        {"nome":"%s","email":"%s","password":"%s"}
        """.formatted(txtNome.getText(), txtEmail.getText(), txtPassword.getText());

        api.post(endpoint, json);
        alert("Registo efetuado");
    }

    @FXML
    public void login() {
        String endpoint = tipoUtilizador.getValue().equals("ESTUDANTE")
                ? "/estudantes/login"
                : "/parceiros/login";

        String json = """
        {"email":"%s","senha":"%s"}
        """.formatted(txtEmail.getText(), txtPassword.getText());

        api.post(endpoint, json);
        alert("Login efetuado");
    }

    private void alert(String m) {
        new Alert(Alert.AlertType.INFORMATION, m).show();
    }
}
