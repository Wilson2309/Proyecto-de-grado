package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getResource("/org/example/views/ShellView.fxml"),
                        "No se encontró ShellView.fxml en src/main/resources/org/example/views/"
                )
        );

        Scene scene = new Scene(loader.load(), 960, 640);

        stage.setTitle("OptiScan Pro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
