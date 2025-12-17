package lp.JavaFXClient_Equipa11.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.ProgramaVoluntariadoDTO;
import lp.JavaFXClient_Equipa11.services.ApiService;

import java.util.List;

public class ProgramaController {

    @FXML private TableView<ProgramaVoluntariadoDTO> programasTable;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Long> idCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> tituloCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> tipoCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Integer> vagasCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        vagasCol.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        listarProgramas();
    }

    @FXML
    public void listarProgramas() {
        try {
            String json = api.get("/programas");
            List<ProgramaVoluntariadoDTO> lista = mapper.readValue(json, new TypeReference<>() {});
            programasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert("Erro ao listar programas: " + e.getMessage());
        }
    }

    @FXML
    public void verificarVagasDisponiveis() {
        alert(api.get("/programas/vagasDisponiveis"));
    }

    @FXML
    public void listarCandidaturasPendentes() {
        alert(api.get("/programas/candidaturasPendentes"));
    }

    @FXML
    public void listarParticipantes() {
        alert(api.get("/programas/participantes"));
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}
