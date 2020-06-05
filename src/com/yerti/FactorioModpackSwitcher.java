package com.yerti;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FactorioModpackSwitcher extends javafx.application.Application {

    private double xOffset = 0;
    private double yOffset = 0;

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_panel.fxml"));
        primaryStage.setTitle("Factorio Switcharoo ");
        stage = primaryStage;
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        root.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            //root.setManaged(false);
            root.setTranslateX(event.getSceneX() - xOffset);
            root.setTranslateY(event.getSceneY() - yOffset);
            //event.consume();
        });


        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });


    }

    public static Stage getStage() {
        return stage;
    }
}
