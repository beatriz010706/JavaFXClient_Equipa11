package lp.JavaFXClient_Equipa11.controller;
/**
 * @author beatriz silva
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.JavaFXClient_Equipa11.modelDTO.ProgramaDTO;
import lp.JavaFXClient_Equipa11.modelDTO.TipoPrograma;
import lp.JavaFXClient_Equipa11.services.ApiService;

import java.util.List;

public class ParceiroController {

    /* LOGIN / REGISTO */
    @FXML private TextField txtNome, txtEmail;
    @FXML private PasswordField txtPassword;

    /* PROGRAMA */
    @FXML private TextField txtTitulo, txtDescricao, txtVagas;
    @FXML private ComboBox<TipoPrograma> tipoPrograma;

    @FXML private TableView<ProgramaDTO> programasTable;
    @FXML private TableColumn<ProgramaDTO, Long> idCol;
    @FXML private TableColumn<ProgramaDTO, String> tituloCol;
    @FXML private TableColumn<ProgramaDTO, TipoPrograma> tipoCol;
    @FXML private TableColumn<ProgramaDTO, Integer> vagasCol;

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
        {"email":"%s","senha":"%s"}
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
            List<ProgramaDTO> lista =
                    mapper.readValue(json, new TypeReference<>() {});
            programasTable.getItems().setAll(lista);
        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    @FXML
    public void registarPrograma() {
        String json = """
        {"titulo":"%s","descricao":"%s","tipo":"%s","vagas":%s}
        """.formatted(
                txtTitulo.getText(),
                txtDescricao.getText(),
                tipoPrograma.getValue(),
                txtVagas.getText()
        );

        api.post("/programas/registar", json);
        alert("Programa registado.");
        listarProgramas();
    }

    @FXML
    public void editarPrograma() {
        ProgramaDTO p = selecionado();
        if (p == null) return;

        String json = """
        {"titulo":"%s","descricao":"%s","tipo":"%s","vagas":%s}
        """.formatted(
                txtTitulo.getText(),
                txtDescricao.getText(),
                tipoPrograma.getValue(),
                txtVagas.getText()
        );

        api.put("/programas/" + p.getId(), json);
        alert("Programa editado.");
        listarProgramas();
    }

    @FXML
    public void eliminarPrograma() {
        ProgramaDTO p = selecionado();
        if (p == null) return;

        api.delete("/programas/" + p.getId());
        alert("Programa eliminado.");
        listarProgramas();
    }

    /* ===================== CANDIDATURAS ===================== */

    @FXML
    public void aprovarCandidatura() {
        ProgramaDTO p = selecionado();
        if (p == null) return;

        api.post("/programas/" + p.getId() + "/candidaturas/aprovar", "");
        alert("Candidatura aprovada.");
    }

    @FXML
    public void rejeitarCandidatura() {
        ProgramaDTO p = selecionado();
        if (p == null) return;

        api.post("/programas/" + p.getId() + "/candidaturas/rejeitar", "");
        alert("Candidatura rejeitada.");
    }

    /* ===================== AUX ===================== */

    private ProgramaDTO selecionado() {
        return programasTable.getSelectionModel().getSelectedItem();
    }

    private void alert(String m) {
        new Alert(Alert.AlertType.INFORMATION, m).show();
    }
}
