package lp.JavaFXClient_Equipa11.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import lp.JavaFXClient_Equipa11.services.ApiService;

public class ProgEstudanteController {

    @FXML private TextField txtHoras;
    private final ApiService api = new ApiService();

    @FXML public void adicionarHoras() { }
    @FXML public void concluirPrograma() { }
    @FXML public void emitirDiploma() { }
    @FXML public void getProgresso() { }
    @FXML public void cancelarParticipacao() { }
}
