package ro.upt.ac.pcbe.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

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
    public void initialize(URL location, ResourceBundle resources) { }

    public void onClickStart(javafx.event.ActionEvent actionEvent) {
        WorkflowSystem.init();
        WorkflowSystem.start();

        Platform.runLater(() -> {
            boolean verifyLock = false;
            boolean verified = false;

            while(WorkflowSystem.getCurrentState() != ProjectPhases.NO_OF_STATES) {
                if (WorkflowSystem.getCurrentState() == ProjectPhases.DOCUMENTATION) {
                    String currentStatus = LabelA.getText();
                    currentStatus += "ok ";
                    LabelA.setText(currentStatus);

                    if (verified) {
                        String currentVerifyStatus = LabelB1.getText();
                        currentVerifyStatus += "nok ";
                        LabelB1.setText(currentVerifyStatus);
                        verified = false;
                    }
                }
                if (WorkflowSystem.getCurrentState() == ProjectPhases.DEBATE) {
                    verified = true;
                }

                if (WorkflowSystem.getCurrentState() == ProjectPhases.PUBLISH) {
                    if (!verifyLock) {
                        LabelB1.setText(LabelB1.getText() + "ok ");
                        verifyLock = true;
                    }

                    LabelC.setText("ok");
                    if (WorkflowSystem.isInternetPublishingDone()) {
                        LabelD.setText("ok");
                    }
                    if (WorkflowSystem.isClientDeliverDone()) {
                        LabelE.setText("ok");
                    }
                }
            }
            WorkflowSystem.stop();
        });
    }

    public void exit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}

