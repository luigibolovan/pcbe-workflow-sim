package ro.upt.ac.pcbe.fxml;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

//import com.company.WorkflowSystem;
//import com.company.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FxController implements Initializable{
	
	 @FXML
	    private TextField txt1;

	    @FXML
	    void Show_Message(ActionEvent event) {
	    	String message= txt1.getText();
	    	JOptionPane.showMessageDialog(null,message);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@FXML private Label lblOutput;

		@FXML
		
		protected void handleButtonAction(ActionEvent e){
		lblOutput.setText("Hello World");
		}
		
public static void main(String[] args) {
	
    
	

	}
}



