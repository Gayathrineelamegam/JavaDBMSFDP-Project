package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import model.GoalPlan;
import model.StudyPlan;
import utility.DBConnection;

public class GoalPlanDAO {

	public ObservableList<GoalPlan> getAllGoals() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<GoalPlan> goals = FXCollections.observableArrayList();
        String query = "SELECT * FROM goalplan";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	System.out.println(resultSet);
            while (resultSet.next()) {
                GoalPlan plan = new GoalPlan();
                LocalDate dueDate = resultSet.getDate("duedate").toLocalDate();
                
                plan.setGoalid(resultSet.getString("goalid"));
                plan.setPlanid(resultSet.getInt("planid"));    
                 
                plan.setGoaldescription(resultSet.getString("goaldescription"));         
                plan.setDuedate(dueDate);
                plan.setIscompleted(resultSet.getString("iscompleted"));
                plan.setRemarks(resultSet.getString("remarks"));
                goals.add(plan);
            }
        }
    	System.out.println(goals);
        return goals;
	}

	public int addGoal(GoalPlan plan) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Add Goal!");
        String query = "INSERT INTO goalplan VALUES (?, ?, ?, ?, ?, ?)";
        int result;  
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1,plan.getGoalid());
            statement.setInt(2, plan.getPlanid());
            statement.setString(3, plan.getGoaldescription());
            statement.setDate(4,java.sql.Date.valueOf(plan.getDuedate()));
            statement.setString(5, plan.getIscompleted());
            statement.setString(6, plan.getRemarks());
            result = statement.executeUpdate();
        }
        return result;
	}
	
	//Delete Goal
		public int deleteGoal(String goalId) throws SQLException {
			int result;
	        String query = "DELETE FROM goalplan WHERE goalid = ?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, goalId);
	            result = statement.executeUpdate();
	        }
	        return result;
	    }

		//Update Goal
		public int updateGoal(String goalid,int planid,String goaldescription, LocalDate duedate, 
				String iscompleted,String remarks) throws SQLException {
	        String query = "UPDATE goalplan SET goaldescription = ?, duedate = ?, iscompleted = ?, remarks = ? WHERE goalid = ?";
	        int result;
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	          
	            statement.setString(1, goaldescription);
	            statement.setDate(2,java.sql.Date.valueOf(duedate));
	            
	            statement.setString(3, iscompleted);
	            statement.setString(4, remarks);
	            statement.setString(5, goalid);
	            result = statement.executeUpdate();
	        }
	        System.out.println(result);
	        return result;
	    }
	
		//Get all goals completed

	    public ObservableList<GoalPlan> getGoalsCompleted(String goalid) throws SQLException 
	    {
	    	ObservableList<GoalPlan> goals = FXCollections.observableArrayList();
	        String query = "SELECT g.* FROM goalplan g " +
	                       //"JOIN studyplan s ON s.planid = g.planid " +
	                       "WHERE g.goalid = ?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, goalid);
	            System.out.println(goalid);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    GoalPlan goal = new GoalPlan();
	                    
	                    goal.setGoalid(resultSet.getString("goalid")); 
	                    goal.setPlanid(resultSet.getInt("planid"));
	        	        goal.setGoaldescription(resultSet.getString("goaldescription"));
	                    goal.setDuedate(resultSet.getDate("duedate").toLocalDate());
	                    goal.setIscompleted(resultSet.getString("iscompleted"));
	                    goal.setRemarks(resultSet.getString("remarks"));
	          	        goals.add(goal);
	                }
	            }
	            //System.out.println(resultSet);
	        }
	        System.out.println(goals);
	        return goals;
	    } 
		

}
