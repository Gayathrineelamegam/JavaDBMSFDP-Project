package fxmlcontroller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StudyPlan;

public class StudyPlanPageController implements Initializable {
	
	 @FXML
	    private TableView<StudyPlan> availableplans_tableview;

	    @FXML
	    private AnchorPane plan_Form;

	    @FXML
	    private Button add_plan;
	    
	    @FXML
	    private Button delete_bug;
	    
	    @FXML
	    private Button update_plan;
	    
	    @FXML
	    private Button search_plan;

	    @FXML
	    private Button GoalBtn;

	    @FXML
	    private AnchorPane main_form;

	    //@FXML
	    //private TextField open_bugs;

	   // @FXML
	   // private TextField resolved_bugs;

	   // @FXML
	   // private TextField total_no_of_bugs;
	    

	    @FXML
	    private TableColumn<StudyPlan, Integer> available_planid;

	    @FXML
	    private TableColumn<StudyPlan, String> available_planname;

	    @FXML
	    private TableColumn<StudyPlan, String> available_plandescription;

	    @FXML
	    private TableColumn<StudyPlan, String> available_startdate;
	    
	    @FXML
	    private TableColumn<StudyPlan, String> available_enddate;
	    
	    @FXML
	    private TableColumn<StudyPlan, String> available_subjectid;
	    
	    @FXML
	    private TextField plan_id;

	    @FXML
	    private TextField plan_name;
	    
	    @FXML
	    private TextArea plan_description;
	    
	    @FXML
	    private DatePicker startdate;
	    
	    @FXML
	    private DatePicker enddate;
	    
	    @FXML
	    private TextField subject_id;
	    
	    
	    @FXML
	    private TextField searchid;
	
	    ObservableList<StudyPlan> planList;
	    

	    StudyPlanController con = new StudyPlanController();
	    
	    @FXML
	    void CategoryForm(ActionEvent event) {

	    }
	    
	    
	    @FXML
	    void startdate(ActionEvent event) {

	    }
	   
	    @FXML
	    void enddate(ActionEvent event) {

	    }
	   
	    @FXML
	    void addPlan(ActionEvent event) {
	    	System.out.println("Add Plan!");
	    	try {
	    		Alert alert;
	    		
	    		
	    		if(plan_id.getText().isEmpty()
	    				||plan_name.getText().isEmpty()
	    				|| plan_description.getText().isBlank()
	    				) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setHeaderText(null);
	        		alert.setContentText("Enter all values!");
	        		alert.showAndWait();
	    		}
	    		else
	    		{
					int result = con.addPlan(new StudyPlan(Integer.parseInt(plan_id.getText()),plan_name.getText(),plan_description.getText(),startdate.getValue(),enddate.getValue(),subject_id.getText()));	        		
					if(result>0)
					{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Plan Added!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't add Plan!");
	            		alert.showAndWait();
	        		}
	    		}
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    @FXML
	    void deletePlan(ActionEvent event) {
	    	System.out.println("Delete Plan!");
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
	    			int result = con.deletePlan(Integer.parseInt(plan_id.getText()));
	        		if(result>0)
	        		{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Plan Deleted!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't delete Plan!");
	            		alert.showAndWait();
	        		}
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	  
	    @FXML
	    void updatePlan(ActionEvent event) {
	    	try
	    	{
	    		Alert alert;
	    		if(plan_id.getText().isEmpty()
	    				||plan_name.getText().isEmpty()
	    				|| plan_description.getText().isBlank()
	    				) {
	    			alert = new Alert(AlertType.ERROR);
	    			alert.setHeaderText(null);
	        		alert.setContentText("Enter all values!");
	        		alert.showAndWait();
	    		}
	    		else
	    		{
	    			
	    			int result = con.updatePlan(Integer.parseInt(plan_id.getText()),plan_name.getText(),plan_description.getText(),startdate.getValue(),enddate.getValue(),subject_id.getText());	        		
	    			if(result>0)
	        		{
	        			showAvailablePlanList();
	        			alert = new Alert(AlertType.INFORMATION);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Plan Updated!");
	            		alert.showAndWait();
	        		}
	        		else
	        		{
	        			alert = new Alert(AlertType.ERROR);
	        			alert.setHeaderText(null);
	            		alert.setContentText("Error! Couldn't update Plan!");
	            		alert.showAndWait();
	        		}
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}

	    }
	    
	    @FXML
	    void searchPlan(ActionEvent event) throws NumberFormatException, SQLException {
	    	//planList =  con.getAllPlans();
	    	planList = con.getPlansAssignedToSubjects(searchid.getText());
	    	for(StudyPlan b:planList)
	    	System.out.println(b.getSubjectid());
	    	
	    	available_planid.setCellValueFactory(new PropertyValueFactory<StudyPlan,Integer>("planid"));
	    	available_planname.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("planname"));
	    	available_plandescription.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("plandescription"));
	    	available_startdate.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("startdate"));
	    	available_enddate.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("enddate"));
	    	available_subjectid.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("subjectid"));
	    	availableplans_tableview.setItems(planList);
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
		
	}
	@list
	  private void showAvailablePlanList() throws SQLException {
    	planList =  con.getAllPlans();
    	for(StudyPlan p:planList)
    	System.out.println(p.getPlanid());
    	
    	available_planid.setCellValueFactory(new PropertyValueFactory<StudyPlan,Integer>("Planid"));
    	available_planname.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("planname"));
    	available_plandescription.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("plandescription"));
    	available_startdate.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("startdate"));
    	available_enddate.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("enddate"));
    	available_subjectid.setCellValueFactory(new PropertyValueFactory<StudyPlan,String>("subjectid"));
    	availableplans_tableview.setItems(planList);

	}
	

    @FXML
    void GoalBtn(ActionEvent event) throws IOException {
    	
    	 GoalBtn.getScene().getWindow().hide();
	    	
			Parent root = FXMLLoader.load(getClass().getResource("/ui/GoalPlanDashBoard.fxml"));
		    Stage primaryStage = new Stage(); 
			Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();

    }

}
