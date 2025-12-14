package lp.JavaFXClient_Equipa11.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/*
 * Controller principal da aplicação JavaFX.
 * Responsável por carregar as diferentes vistas (FXML)
 * para a zona central da janela.
 */
public class MainController {

    @FXML
    private BorderPane mainPane;

    /*
     * Método chamado automaticamente quando o FXML é carregado.
     * Carrega uma vista por defeito (Menu Estudante).
     */
    @FXML
    public void initialize() {
        carregarVista("/estudantes-view.fxml");
    }

    // BOTÕES DO MENU 

    @FXML
    private void abrirMenuEstudante() {
        carregarVista("/estudantes-view.fxml");
    }

    @FXML
    private void abrirMenuParceiro() {
        carregarVista("/parceiros-view.fxml");
    }

    @FXML
    private void abrirMenuProgramas() {
        carregarVista("/programas-view.fxml");
    }

    @FXML
    private void abrirMenuProgramaEstudante() {
        carregarVista("/programa-estudante-view.fxml");
    }

    @FXML
    private void abrirMenuCandidaturas() {
        carregarVista("/candidaturas-view.fxml");
    }

    //  MÉTODO AUXILIAR

    /*
     * Carrega um ficheiro FXML para o centro do BorderPane.
     * @param fxml caminho do ficheiro FXML
     */
    private void carregarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();
            mainPane.setCenter(view);
        } catch (Exception e) {
            System.err.println("Erro ao carregar a vista: " + fxml);
            e.printStackTrace();
        }
    }
}