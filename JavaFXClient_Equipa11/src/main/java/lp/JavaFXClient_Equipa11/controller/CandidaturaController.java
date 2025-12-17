package lp.JavaFXClient_Equipa11.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.CandidaturaDTO;
import lp.JavaFXClient_Equipa11.services.ApiService;

import java.util.List;

public class CandidaturaController {

    @FXML private TableView<CandidaturaDTO> candidaturasTable;
    @FXML private TableColumn<CandidaturaDTO, Long> idCol;
    @FXML private TableColumn<CandidaturaDTO, Long> estudanteCol;
    @FXML private TableColumn<CandidaturaDTO, Long> programaCol;
    @FXML private TableColumn<CandidaturaDTO, String> estadoCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudanteCol.setCellValueFactory(new PropertyValueFactory<>("estudanteId"));
        programaCol.setCellValueFactory(new PropertyValueFactory<>("programaId"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        candidaturasTable.setPlaceholder(new Label("Sem candidaturas."));
        listarCandidaturas();
    }

    @FXML
    public void listarCandidaturas() {
        try {
            String json = api.get("/candidaturas");
            List<CandidaturaDTO> lista = mapper.readValue(json, new TypeReference<>() {});
            candidaturasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert("Erro ao listar candidaturas: " + e.getMessage());
        }
    }

    @FXML
    public void atualizarEstado() {
        CandidaturaDTO c = candidaturasTable.getSelectionModel().getSelectedItem();
        if (c == null) { alert("Selecione uma candidatura."); return; }
        api.put("/candidaturas/" + c.getId() + "/atualizarEstado", "");
        alert("Estado atualizado.");
        listarCandidaturas();
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
