package fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.StudyPlanController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;

public class AdminController implements Initializable{
	
	StudyPlanController con = new StudyPlanController();
	Alert alert;
	
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
    	
    	boolean result = con.adminValidate(new Admin(username.getText(),password.getText()));
		 
		 if(result == true)
		 {
			 alert = new Alert(AlertType.INFORMATION);
			 alert.setHeaderText(null);
			 alert.setContentText("Login Successful");
			 alert.showAndWait();
			 
			 login.getScene().getWindow().hide();
		    	
				Parent root = FXMLLoader.load(getClass().getResource("/ui/StudyPlanDashBoard.fxml"));
			    Stage primaryStage = new Stage(); 
				Scene scene = new Scene(root);
			    primaryStage.setScene(scene);
			    primaryStage.show();
		 }
       
	
}

	/*private boolean adminValidate(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}*/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
