package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.EstudanteDTO;
import lp.JavaFXClient_Equipa11.services.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EstudanteController {

    @FXML private TableView<EstudanteDTO> estudantesTable;
    @FXML private TableColumn<EstudanteDTO, Long> idCol;
    @FXML private TableColumn<EstudanteDTO, String> nomeCol;
    @FXML private TableColumn<EstudanteDTO, String> emailCol;
    @FXML private TableColumn<EstudanteDTO, String> cursoCol;
    @FXML private TableColumn<EstudanteDTO, String> numeroAlunoCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cursoCol.setCellValueFactory(new PropertyValueFactory<>("curso"));
        numeroAlunoCol.setCellValueFactory(new PropertyValueFactory<>("numeroAluno"));

        loadEstudantes();
    }

    @FXML
    public void onRefresh() {
        loadEstudantes();
    }

    private void loadEstudantes() {
        try {
            String json = api.get("/estudantes");
            if (json.startsWith("ERROR:")) {
                showAlert(Alert.AlertType.ERROR, json);
                return;
            }
            List<EstudanteDTO> estudantes = mapper.readValue(json, new TypeReference<List<EstudanteDTO>>() {});
            estudantesTable.getItems().setAll(estudantes);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro: " + e.getMessage());
        }
    }

    @FXML
    public void onAddEstudante() {
        showAlert(Alert.AlertType.INFORMATION, "Abrir formulário de criação de Estudante");
    }

    @FXML
    public void onEditEstudante() {
        EstudanteDTO selected = estudantesTable.getSelectionModel().getSelectedItem();
        if (selected == null) { showAlert(Alert.AlertType.WARNING, "Selecione um estudante"); return; }
        showAlert(Alert.AlertType.INFORMATION, "Abrir formulário de edição: " + selected.getNome());
    }

    @FXML
    public void onDeleteEstudante() {
        EstudanteDTO selected = estudantesTable.getSelectionModel().getSelectedItem();
        if (selected == null) { showAlert(Alert.AlertType.WARNING, "Selecione um estudante"); return; }
        String result = api.delete("/estudantes/" + selected.getId());
        showAlert(Alert.AlertType.INFORMATION, result);
        loadEstudantes();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}//fim classe
