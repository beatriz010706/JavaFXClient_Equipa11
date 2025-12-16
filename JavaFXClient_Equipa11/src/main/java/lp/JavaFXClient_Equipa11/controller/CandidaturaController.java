package lp.JavaFXClient_Equipa11.controller;

import lp.JavaFXClient_Equipa11.modelDTO.Estado;
import lp.JavaFXClient_Equipa11.modelDTO.EstudanteDTO;
import lp.JavaFXClient_Equipa11.modelDTO.ProgramaDTO;
import lp.JavaFXClient_Equipa11.modelDTO.CandidaturaDTO;
import lp.JavaFXClient_Equipa11.services.ApiService;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * @author gonçalo silva
 */


public class CandidaturaController {

    @FXML private TableView<CandidaturaDTO> candidaturasTable;
    @FXML private TableColumn<CandidaturaDTO, Long> idCol;
    @FXML private TableColumn<CandidaturaDTO, String> estudanteCol;
    @FXML private TableColumn<CandidaturaDTO, String> programaCol;
    @FXML private TableColumn<CandidaturaDTO, Estado> estadoCol;
    @FXML private ComboBox<EstudanteDTO> estudanteCombo;
    @FXML private ComboBox<ProgramaDTO> programaCombo;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        estudanteCol.setCellValueFactory(new PropertyValueFactory<>("estudanteId"));
        programaCol.setCellValueFactory(new PropertyValueFactory<>("programaId"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        listar();
    }
    
    @FXML
    public void registarCandidatura() {
        EstudanteDTO e = estudanteCombo.getValue();
        ProgramaDTO p = programaCombo.getValue();
        
        if (e == null || p == null) {
            alert("Escolha estudante e programa!");
            return;
        }

        String json = """
            {
              "estudanteId": %s,
              "programaId": %s
            }
            """.formatted(e.getId(), p.getId());

        api.post("/candidaturas/", json);
        alert("Candidatura enviada!");
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

        api.put("/candidaturas/" + c.getId() + "/estado",
                "{\"estado\":\"ACEITE\"}");
        alert("Estado atualizado!");
        listar();
    }

    private void alert(String m) {
        new Alert(Alert.AlertType.INFORMATION, m).show();
        
    }
}