package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.CandidaturaDTO;
import lp.JavaFXClient_Equipa11.modelDTO.Estado;
import lp.JavaFXClient_Equipa11.services.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CandidaturaController {

    @FXML private TableView<CandidaturaDTO> candidaturasTable;
    @FXML private TableColumn<CandidaturaDTO, Long> idCol;
    @FXML private TableColumn<CandidaturaDTO, String> estudanteCol;
    @FXML private TableColumn<CandidaturaDTO, String> programaCol;
    @FXML private TableColumn<CandidaturaDTO, Estado> estadoCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudanteCol.setCellValueFactory(new PropertyValueFactory<>("estudanteNome"));
        programaCol.setCellValueFactory(new PropertyValueFactory<>("programaTitulo"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        listar();
    }

    @FXML
    public void listar() {
        try {
        	String json = api.get("/candidaturas/todas");
            List<CandidaturaDTO> lista =
                    mapper.readValue(json, new TypeReference<>() {});
            candidaturasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    @FXML
    public void atualizarEstado() {
        CandidaturaDTO c = candidaturasTable.getSelectionModel().getSelectedItem();
        if (c == null) return;
        api.put("/candidaturas/" + c.getId() + "/estado", "{\"estado\":\"APROVADA\"}");
        alert("Estado atualizado!");
        listar();
    }

    private void alert(String m) { new Alert(Alert.AlertType.INFORMATION, m).show(); }
}
