package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.ProgramaVoluntariadoDTO;
import lp.JavaFXClient_Equipa11.modelDTO.TipoPrograma;
import lp.JavaFXClient_Equipa11.services.ApiService;

import java.util.List;

public class ParceiroController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtVagas;
    @FXML private TextField txtLocal;
    @FXML private ComboBox<TipoPrograma> tipoPrograma;
    
    @FXML private TableView<ProgramaVoluntariadoDTO> programasTable;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Long> idCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> tituloCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> tipoCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> localCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Integer> vagasCol;


    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        // Preenche ComboBox com tipos de programa
        tipoPrograma.getItems().setAll(TipoPrograma.values());

        // Configura colunas da tabela
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo")); // precisa do getter getTipo()
        localCol.setCellValueFactory(new PropertyValueFactory<>("local"));
        vagasCol.setCellValueFactory(new PropertyValueFactory<>("vagas"));

        // Lista programas ao iniciar
        listarProgramas();
    }

    /** =================== CRUD Programas =================== */

    @FXML
    public void listarProgramas() {
        try {
            String json = api.get("/parceiros/programas");
            List<ProgramaVoluntariadoDTO> lista = mapper.readValue(json, new TypeReference<>() {});
            programasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert("Erro ao listar programas: " + e.getMessage());
        }
    }

    public void registarPrograma() {
        String endpoint = "/programas";

        // Validação        
        if (txtTitulo.getText().isBlank() ||
            txtDescricao.getText().isBlank() ||
            txtVagas.getText().isBlank() ||
            tipoPrograma.getValue() == null) {

            alert("Preencha todos os campos do programa.");
            return;
        }

        int vagas;
        try {
            vagas = Integer.parseInt(txtVagas.getText());
        } catch (NumberFormatException e) {
            alert("Número de vagas inválido.");
            return;
        }

        String json = """
            {
              "titulo":"%s",
              "descricao":"%s",
              "vagas":%d,
              "tipo":"%s"
            }
            """.formatted(
                txtTitulo.getText(),
                txtDescricao.getText(),
                vagas,
                tipoPrograma.getValue()
            );

        try {
            api.post(endpoint, json);
            alert("Programa registado com sucesso!");
            listarProgramas(); // refresh da tabela
        } catch (Exception e) {
            alert("Erro ao registar programa.");
            e.printStackTrace();
        }
    }


    @FXML
    public void editarPrograma() {
        ProgramaVoluntariadoDTO selecionado = programasTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { alert("Selecione um programa."); return; }

        try {
            ProgramaVoluntariadoDTO p = getProgramaFromFields();
            String json = mapper.writeValueAsString(p);
            api.put("/programas/" + selecionado.getId(), json);
            alert("Programa editado com sucesso!");
            listarProgramas();
        } catch (Exception e) {
            alert("Erro ao editar programa: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarPrograma() {
        ProgramaVoluntariadoDTO selecionado = programasTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { alert("Selecione um programa."); return; }

        api.delete("/programas/" + selecionado.getId());
        alert("Programa eliminado.");
        listarProgramas();
    }

    /** =================== Candidaturas =================== */

    @FXML
    public void aprovarCandidatura() {
        ProgramaVoluntariadoDTO selecionado = programasTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { alert("Selecione um programa."); return; }

        api.post("/programas/" + selecionado.getId() + "/candidaturas/aprovar", "");
        alert("Candidatura aprovada.");
    }

    @FXML
    public void rejeitarCandidatura() {
        ProgramaVoluntariadoDTO selecionado = programasTable.getSelectionModel().getSelectedItem();
        if (selecionado == null) { alert("Selecione um programa."); return; }

        api.post("/programas/" + selecionado.getId() + "/candidaturas/rejeitar", "");
        alert("Candidatura rejeitada.");
    }

    /** =================== Auxiliares =================== */

    private ProgramaVoluntariadoDTO getProgramaFromFields() {
        ProgramaVoluntariadoDTO p = new ProgramaVoluntariadoDTO();
        p.setTitulo(txtTitulo.getText());
        p.setDescricao(txtDescricao.getText());
        p.setTipo(tipoPrograma.getValue() != null ? tipoPrograma.getValue().toString() : "");
        p.setLocal(txtLocal.getText());
        p.setVagas(Integer.parseInt(txtVagas.getText()));
        p.setVagasOcupadas(0); // inicial
        return p;
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }
}//fim classe
