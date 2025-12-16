package lp.JavaFXClient_Equipa11.controller;

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
        tipoUtilizador.getItems().addAll("ESTUDANTE", "PARCEIRO");
        tipoUtilizador.getSelectionModel().selectFirst();
        tipoUtilizador.setOnAction(e -> atualizarCampos());
        atualizarCampos();
    }

    private void atualizarCampos() {
        boolean isEstudante = tipoUtilizador.getValue().equals("ESTUDANTE");
        txtCurso.setDisable(!isEstudante);
        txtNumeroAluno.setDisable(!isEstudante);
        tipoParceiro.setDisable(isEstudante);
    }

    @FXML
    public void registar() {
    	String endpoint;
    	String json;
    
    		if (tipoUtilizador.getValue().equals("ESTUDANTE")) {
    			endpoint = "/estudantes/registar";
    			if (txtNumeroAluno.getText().isBlank()) {
    				alert("Número de aluno obrigatório.");
    				return;
    			}
    int numeroAluno = Integer.parseInt(txtNumeroAluno.getText()); // COVERTER 1º STRING PARA INT

    json = """
    {"nome":"%s","email":"%s","password":"%s","curso":"%s","numeroAluno":%d}
    """.formatted(
    		txtNome.getText(),
    		txtEmail.getText(),
    		txtPassword.getText(),
    		txtCurso.getText(),
    		numeroAluno
    		);
    	}
    }

    @FXML
    public void login() {
        String endpoint = tipoUtilizador.getValue().equals("ESTUDANTE") ? "/estudantes/login" : "/parceiros/login";
        String json = """
                {"email":"%s","senha":"%s"}
                """.formatted(txtEmail.getText(), txtPassword.getText());
        api.post(endpoint, json);
        alert("Login efetuado.");
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}