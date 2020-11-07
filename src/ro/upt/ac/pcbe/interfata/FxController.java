package ro.upt.ac.pcbe.interfata;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ro.upt.ac.pcbe.system.WorkflowSystem;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class FxController implements Initializable {

    @FXML
    private Button Xbutton;

    @FXML
    private Label LabelA;

    @FXML
    private Label LabelB1;

    @FXML
    private Label LabelC;

    @FXML
    private Label LabelD;

    @FXML
    private Label LabelE;

    @FXML
    private Label LabelB2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickStart(javafx.event.ActionEvent actionEvent) {
        WorkflowSystem.init();
        WorkflowSystem.start();
    }

    public void exit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}

