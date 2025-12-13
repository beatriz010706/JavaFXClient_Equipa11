package lp.JavaFXClient_Equipa11;
/**
 * @author equipa11
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login-view.fxml")
        );
        Scene scene = new Scene(loader.load());
        stage.setTitle("Plataforma de Voluntariado");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
