package ro.upt.ac.pcbe.interfata;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
        private  double posX,posY;
    public static void main(String[] args) {
            Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane mainPane = (Pane) FXMLLoader.load(Main.class.getResource("Interfata.fxml"));
        primaryStage.setScene(new Scene(mainPane,435,400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mainPane.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                posX = mouseEvent.getSceneX();
                posY = mouseEvent.getSceneY();
            }
        });

        mainPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() - posX);
                primaryStage.setY(mouseEvent.getScreenY() - posY);
            }
        });
    }
}
