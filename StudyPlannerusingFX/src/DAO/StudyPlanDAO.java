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
import model.StudyPlan;
import utility.DBConnection;

public class StudyPlanDAO {

	public ObservableList<StudyPlan> getAllPlans() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<StudyPlan> plans = FXCollections.observableArrayList();
        String query = "SELECT * FROM studyplan";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	System.out.println(resultSet);
            while (resultSet.next()) {
                StudyPlan plan = new StudyPlan();
                LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                LocalDate endDate = resultSet.getDate("enddate").toLocalDate();

                plan.setPlanid(resultSet.getInt("planid"));    
                plan.setPlanname(resultSet.getString("planname"));      
                plan.setPlandescription(resultSet.getString("plandescription"));         
                plan.setStartdate(startDate);
                plan.setEnddate(endDate);
             
                plan.setSubjectid(resultSet.getString("subjectid"));
                plans.add(plan);
            }
        }
    	System.out.println(plans);
        return plans;
	}

	public int addPlan(StudyPlan plan) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Add Plan!");
        String query = "INSERT INTO studyplan VALUES (?, ?, ?, ?, ?, ?)";
        int result;  
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setInt(1,plan.getPlanid());
            statement.setString(2, plan.getPlanname());
            statement.setString(3, plan.getPlandescription());
            statement.setDate(4,java.sql.Date.valueOf(plan.getStartdate()));
            statement.setDate(5, java.sql.Date.valueOf(plan.getStartdate()));
            statement.setString(6, plan.getSubjectid());
            result = statement.executeUpdate();
        }
        return result;
	}
	
	//Delete Plan
		public int deletePlan(int planId) throws SQLException {
			int result;
	        String query = "DELETE FROM studyplan WHERE planid = ?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, planId);
	            result = statement.executeUpdate();
	        }
	        return result;
	    }

		//Update Plan
		public int updatePlan(int planid, String planname, String plandescription, LocalDate startdate, LocalDate enddate,
				String subjectid) throws SQLException {
	        String query = "UPDATE studyplan SET planname = ?, plandescription = ?, startdate = ?, enddate = ?, subjectid = ? WHERE planid = ?";
	        int result;
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, planname);
	            statement.setString(2, plandescription);
	            statement.setDate(3,java.sql.Date.valueOf(startdate));
	            statement.setDate(4, java.sql.Date.valueOf(enddate));
	            statement.setString(5, subjectid);
	            statement.setInt(6, planid);
	            result = statement.executeUpdate();
	        }
	        System.out.println(result);
	        return result;
	    }
		
		
		//Get all plans assigned to a subjectid with a specific name

	    public ObservableList<StudyPlan> getPlansAssignedToSubjects(String subjectid) throws SQLException {
	    	ObservableList<StudyPlan> plans = FXCollections.observableArrayList();
	        String query = "SELECT p.* FROM studyplan p " +
	                       "JOIN subjectplan s ON p.subjectid = s.subjectid " +
	                       "WHERE s.subjectid = ?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, subjectid);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    StudyPlan plan = new StudyPlan();
	                 
	                    plan.setPlanid(resultSet.getInt("planid"));
	                    plan.setPlanname(resultSet.getString("planname"));
	                    plan.setPlandescription(resultSet.getString("plandescription"));
	                
	                    plan.setStartdate( resultSet.getDate("startDate").toLocalDate());
	                    plan.setEnddate( resultSet.getDate("EndDate").toLocalDate());
	                    plan.setSubjectid(resultSet.getString("subjectid"));
	          	        plans.add(plan);
	                }
	            }
	        }
	        System.out.println(plans);
	        return plans;
	    }
		

}
