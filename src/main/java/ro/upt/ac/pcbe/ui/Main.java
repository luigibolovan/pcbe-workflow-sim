package ro.upt.ac.pcbe.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double posX,posY;
    public static void main(String[] args) {
            Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Pane mainPane = FXMLLoader.load(getClass().getResource("/interfaceXML.fxml"));
        primaryStage.setScene(new Scene(mainPane,435,400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mainPane.setOnMousePressed(mouseEvent -> {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
        });

        mainPane.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - posX);
            primaryStage.setY(mouseEvent.getScreenY() - posY);
        });
    }
}
