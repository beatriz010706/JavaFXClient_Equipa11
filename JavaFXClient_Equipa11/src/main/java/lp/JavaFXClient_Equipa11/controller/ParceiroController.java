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

    @FXML private TextField txtNome, txtEmail, txtPassword;
    @FXML private TextField txtTitulo, txtDescricao, txtVagas;
    @FXML private ComboBox<TipoPrograma> tipoPrograma;
    @FXML private TableView<ProgramaVoluntariadoDTO> programasTable;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Long> idCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, String> tituloCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, TipoPrograma> tipoCol;
    @FXML private TableColumn<ProgramaVoluntariadoDTO, Integer> vagasCol;

    private final ApiService api = new ApiService();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        tipoPrograma.getItems().setAll(TipoPrograma.values());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        vagasCol.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        listarProgramas();
    }

    /* ===================== AUTENTICAÇÃO ===================== */
    @FXML
    public void registar() {
        String json = """
                {"nome":"%s","email":"%s","password":"%s"}
                """.formatted(txtNome.getText(), txtEmail.getText(), txtPassword.getText());
        api.post("/parceiros/registar", json);
        alert("Parceiro registado com sucesso.");
    }

    @FXML
    public void login() {
        String json = """
                {"email":"%s","password":"%s"}
                """.formatted(txtEmail.getText(), txtPassword.getText());
        api.post("/parceiros/login", json);
        alert("Login efetuado.");
        listarProgramas();
    }

    /* ===================== PROGRAMAS ===================== */
    @FXML
    public void listarProgramas() {
        try {
            String json = api.get("/parceiros/programas");
            List<ProgramaVoluntariadoDTO> lista = mapper.readValue(json, new TypeReference<>() {});
            programasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    @FXML
    public void registarPrograma() {
        try {
        	ProgramaVoluntariadoDTO p = getProgramaFromFields();
            String json = new ObjectMapper().writeValueAsString(p);
            api.post("/programas/registar", json);
            alert("Programa registado.");
            listarProgramas();
        } catch (Exception e) {
            alert("Erro: " + e.getMessage());
        }
    }

    @FXML
    public void editarPrograma() {
        try {
        	ProgramaVoluntariadoDTO p = selecionado();
            if (p == null) return;
            ProgramaVoluntariadoDTO novo = getProgramaFromFields();
            String json = new ObjectMapper().writeValueAsString(novo);
            api.put("/programas/" + p.getId(), json);
            alert("Programa editado.");
            listarProgramas();
        } catch (Exception e) {
            alert("Erro: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarPrograma() {
    	ProgramaVoluntariadoDTO p = selecionado();
        if (p == null) return;
        api.delete("/programas/" + p.getId());
        alert("Programa eliminado.");
        listarProgramas();
    }

    @FXML
    public void aprovarCandidatura() {
    	ProgramaVoluntariadoDTO p = selecionado();
        if (p == null) return;
        api.post("/programas/" + p.getId() + "/candidaturas/aprovar", "");
        alert("Candidatura aprovada.");
    }

    @FXML
    public void rejeitarCandidatura() {
    	ProgramaVoluntariadoDTO p = selecionado();
        if (p == null) return;
        api.post("/programas/" + p.getId() + "/candidaturas/rejeitar", "");
        alert("Candidatura rejeitada.");
    }

    private ProgramaVoluntariadoDTO selecionado() {
        return programasTable.getSelectionModel().getSelectedItem();
    }

    private ProgramaVoluntariadoDTO getProgramaFromFields() {
    	ProgramaVoluntariadoDTO p = new ProgramaVoluntariadoDTO();
        p.setTitulo(txtTitulo.getText());
        p.setDescricao(txtDescricao.getText());
        p.setTipo(tipoPrograma.getValue().toString()); // converte para string
        p.setVagas(Integer.parseInt(txtVagas.getText()));
        return p;
    }

    private void alert(String m) {
        new Alert(Alert.AlertType.INFORMATION, m).show();
    }
}
