package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;

/**
 * Controlador del shell principal (cabecera, barra lateral y área de contenido).
 */
public class ShellController {

    @FXML
    private BorderPane shellRoot;

    @FXML
    private HBox headerBar;

    @FXML
    private Label appTitleLabel;

    @FXML
    private VBox sidebar;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button seguridadButton;

    @FXML
    private Button limpiezaButton;

    @FXML
    private StackPane contentArea;

    @FXML
    private Label mainContentLabel;

    @FXML
    private void initialize() {
        // Punto de extensión: estado inicial, listeners ligeros, etc.
    }

    /**
     * Carga una vista FXML y la coloca como único hijo del área central.
     *
     * @param fxmlPath ruta absoluta en el classpath (p. ej. {@code /org/example/views/DashboardView.fxml})
     */
    private void loadView(String fxmlPath) {
        try {
            URL location = Objects.requireNonNull(
                    ShellController.class.getResource(fxmlPath),
                    "Recurso FXML no encontrado en classpath: " + fxmlPath
            );

            FXMLLoader loader = new FXMLLoader(location);
            Node view = loader.load();

            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToDashboard() {
        loadView("/org/example/views/DashboardView.fxml");
    }

    @FXML
    private void goToSeguridad() {
        loadView("/org/example/views/SeguridadView.fxml");
    }

    @FXML
    private void goToLimpieza() {
        loadView("/org/example/views/LimpiezaView.fxml");
    }
}

