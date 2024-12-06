package fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import controller.GoalMainController;
import controller.StudyPlanController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GoalPlan;
import model.StudyPlan;

public class GoalPlanPageController implements Initializable {
	
	 @FXML
	    private TableView<GoalPlan> availableplans_tableview;

	    @FXML
	    private AnchorPane plan_Form;

	    @FXML
	    private Button add_goal;
	    
	    @FXML
	    private Button delete_goal;
	    
	    @FXML
	    private Button update_goal;
	    
	    @FXML
	    private Button search_goal;

	    @FXML
	    private Button HomeBtn;

	    @FXML
	    private AnchorPane main_form;

	    @FXML
	    private TextField remarks;

	   // @FXML
	   // private TextField resolved_bugs;

	   // @FXML
	   // private TextField total_no_of_bugs;
	    

	    @FXML
	    private TableColumn<GoalPlan, String> available_goalid;

	    @FXML
	    private TableColumn<GoalPlan, Integer> available_planid;

	    @FXML
	    private TableColumn<GoalPlan, String> available_goaldescription;

	    @FXML
	    private TableColumn<GoalPlan, String> available_duedate;
	    
	    @FXML
	    private TableColumn<GoalPlan, String> available_iscompleted;
	    
	    @FXML
	    private TableColumn<GoalPlan, String> available_remarks;
	    
	    @FXML
	    private TextField goal_id;

	    @FXML
	    private TextField plan_id;
	    
	    @FXML
	    private TextArea goal_description;
	    
	    @FXML
	    private DatePicker due_date;
	    
	    @FXML
	    private TextField searchid;
	    
	    @FXML
	    private RadioButton completed_yes;
	    
	    @FXML
	    private RadioButton completed_no;
	    
	    @FXML
	    private ToggleGroup myToggleGroup;
	    
	
	    ObservableList<GoalPlan> goalList;
	    

	    GoalMainController con = new GoalMainController();
	    
	    @FXML
	    void CategoryForm(ActionEvent event) {

	    }
	    
	    
	    @FXML
	    void duedate(ActionEvent event) {

	    }
	   
	    @FXML
	    void addGoal(ActionEvent event) {
	    	System.out.println("Add Goal!");
	    	try {
	    		
	    		RadioButton selectedRadioButton = (RadioButton) myToggleGroup.getSelectedToggle();
    			String selectedStatus = selectedRadioButton.getText();
    			
    			// LocalDate localDate = duedate.getValue();  // Now this should not be null
    	        // Date dueDateSQL = Date.valueOf(localDate);
    			
	    		Alert alert;
	    		if(goal_id.getText().isEmpty()
	    	            ||plan_id.getText().isEmpty()
	    				|| goal_description.getText().isBlank()
	    				) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setHeaderText(null);
	        		alert.setContentText("Enter all values!");
	        		alert.showAndWait();
	    		}
	    		else
	    		{
					int result = con.addGoal(new GoalPlan(goal_id.getText(),Integer.parseInt(plan_id.getText()),goal_description.getText(),due_date.getValue(),selectedStatus,remarks.getText()));	        		
					if(result>0)
					{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Goal Added!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't add Goal!");
	            		alert.showAndWait();
	        		}
	    		}
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    @FXML
	    void deleteGoal(ActionEvent event) {
	    	System.out.println("Delete Goal!");
	    	try
	    	{
	    		Alert alert;
	    		if(plan_id.getText().isEmpty()) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setHeaderText(null);
	        		alert.setContentText("Enter all values!");
	        		alert.showAndWait();
	    		}
	    		else
	    		{
	    			int result = con.deleteGoal(goal_id.getText());
	        		if(result>0)
	        		{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Goal Deleted!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't delete Goal!");
	            		alert.showAndWait();
	        		}
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	  
	    @FXML
	    void updateGoal(ActionEvent event) {
	    	try
	    	{
	    		RadioButton selectedRadioButton = (RadioButton) myToggleGroup.getSelectedToggle();
    			String selectedStatus = selectedRadioButton.getText();
    			
	    		Alert alert;
	    		if(plan_id.getText().isEmpty()
	    				||goal_id.getText().isEmpty()
	    				||goal_description.getText().isBlank()
	    				) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setHeaderText(null);
	        		alert.setContentText("Enter all values!");
	        		alert.showAndWait();
	    		}
	    		else
	    		{
	    			int result = con.updateGoal(goal_id.getText(),Integer.parseInt(plan_id.getText()),goal_description.getText(),due_date.getValue(),selectedStatus,remarks.getText());	        		
	    			if(result>0)
	        		{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Goal Updated!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't update Goal!");
	            		alert.showAndWait();
	        		}
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}

	    }
	    
	    @FXML
	    void searchGoal(ActionEvent event) throws NumberFormatException, SQLException {
	 	    goalList = con.getGoalsCompleted(searchid.getText());
	    	for(GoalPlan g:goalList)
	    	System.out.println(g.getGoalid());
	    	
	    	available_goalid.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("goalid"));
	    	available_planid.setCellValueFactory(new PropertyValueFactory<GoalPlan,Integer>("planid"));
	    	available_goaldescription.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("goaldescription"));
	    	available_duedate.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("duedate"));
	    	available_iscompleted.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("iscompleted"));
	    	available_remarks.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("remarks"));
	    	availableplans_tableview.setItems(goalList);
	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		try {
			showAvailablePlanList();
		//	getBugIds();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myToggleGroup = new ToggleGroup();
		completed_yes.setToggleGroup(myToggleGroup);
		completed_no.setToggleGroup(myToggleGroup);		
	}
	
	/*@FXML
	public void handleRadioButtonAction(ActionEvent event) {
		// Retrieve selected RadioButton
        RadioButton selectedRadioButton = (RadioButton) myToggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            // Handle case where no RadioButton is selected
            System.out.println("Please select a status option.");
            return;
        }

        String selectedStatus = selectedRadioButton.getText();
        System.out.println("Selected Radio Button: " + selectedStatus);
    }*/
	@list
	  private void showAvailablePlanList() throws SQLException {
    	goalList =  con.getAllGoals();
    	for(GoalPlan g:goalList)
    	System.out.println(g.getGoalid());
    	
    	available_goalid.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("goalid"));
    	available_planid.setCellValueFactory(new PropertyValueFactory<GoalPlan,Integer>("planid"));
    	available_goaldescription.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("goaldescription"));
    	available_duedate.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("duedate"));
    	available_iscompleted.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("iscompleted"));
    	available_remarks.setCellValueFactory(new PropertyValueFactory<GoalPlan,String>("remarks"));
    	availableplans_tableview.setItems(goalList);

	}
	


    @FXML
    void HomeBtn(ActionEvent event) throws IOException {
    	
    	 HomeBtn.getScene().getWindow().hide();
	    	
			Parent root = FXMLLoader.load(getClass().getResource("/ui/AdminPage.fxml"));
		    Stage primaryStage = new Stage(); 
			Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();

    }


}
