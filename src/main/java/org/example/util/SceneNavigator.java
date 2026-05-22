package org.example.util;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public final class SceneNavigator {

    private static final Duration FADE_OUT = Duration.millis(220);
    private static final Duration FADE_IN = Duration.millis(320);

    private SceneNavigator() {
    }

    public static void transition(
            Stage stage,
            Parent currentRoot,
            Class<?> resourceOwner,
            String fxmlPath,
            double width,
            double height
    ) {
        FadeTransition fadeOut = new FadeTransition(FADE_OUT, currentRoot);
        fadeOut.setFromValue(currentRoot.getOpacity() > 0 ? currentRoot.getOpacity() : 1);
        fadeOut.setToValue(0);

        fadeOut.setOnFinished(event -> loadAndFadeIn(stage, resourceOwner, fxmlPath, width, height));
        fadeOut.play();
    }

    private static void loadAndFadeIn(
            Stage stage,
            Class<?> resourceOwner,
            String fxmlPath,
            double width,
            double height
    ) {
        try {
            URL location = Objects.requireNonNull(
                    resourceOwner.getResource(fxmlPath),
                    "FXML no encontrado: " + fxmlPath
            );

            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();
            root.setOpacity(0);

            Scene scene = new Scene(root, width, height);
            stage.setTitle("OptiScan Pro");
            stage.setScene(scene);

            FadeTransition fadeIn = new FadeTransition(FADE_IN, root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

