package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.StudyPlanDAO;
import DAO.AdminDAO;
import DAO.GoalPlanDAO;
import javafx.collections.ObservableList;
import model.Admin;
import model.GoalPlan;
import model.StudyPlan;

public class GoalMainController {
	
	AdminDAO adminDAO = new AdminDAO();
	StudyPlanDAO planDAO = new StudyPlanDAO();
	GoalPlanDAO goalDAO = new GoalPlanDAO();
	
	public boolean adminValidate(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		
		return adminDAO.adminValidate(admin);
		
		
	}
	public ObservableList<StudyPlan> getAllPlans() throws SQLException
	{
		return planDAO.getAllPlans();
	}

	
	public int addPlan(StudyPlan plan) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Add Plan!");
		int result = planDAO.addPlan(plan);
		return result;
	}
    

	public int deletePlan(int planID) throws SQLException {
		// TODO Auto-generated method stub
		int result = planDAO.deletePlan(planID);
		return result;
	}
	
	
	public ObservableList<StudyPlan> getPlansAssignedToSubjects(String subjectid) throws SQLException
	{
		return planDAO.getPlansAssignedToSubjects(subjectid);
		
	}
	public int updatePlan(int planid, String planname, String plandescription, LocalDate startdate, LocalDate enddate,
				String subjectid) throws SQLException {
		// TODO Auto-generated method stub
			int result;
				result = planDAO.updatePlan(planid,planname,plandescription,startdate,enddate,subjectid);
			
			// TODO Auto-generated catch block
		
			// TODO Auto-generated catch block
		return result;
	}
	
	public ObservableList<GoalPlan> getAllGoals() throws SQLException
	{
		return goalDAO.getAllGoals();
	}
	
	public int addGoal(GoalPlan goal) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Add Goal!");
		int result = goalDAO.addGoal(goal);
		return result;
	}
    

	public int deleteGoal(String goalID) throws SQLException {
		// TODO Auto-generated method stub
		int result = goalDAO.deleteGoal(goalID);
		return result;
	}
	
	public int updateGoal(String goalid, int planid,String plandescription, LocalDate duedate, 
			String iscompleted,String remarks) throws SQLException {
	// TODO Auto-generated method stub
		int result;
			result = goalDAO.updateGoal(goalid,planid,plandescription,duedate,iscompleted,remarks);
		
		// TODO Auto-generated catch block
	
		// TODO Auto-generated catch block
	return result;
}
	public ObservableList<GoalPlan> getGoalsCompleted(String goalid) throws SQLException {
		// TODO Auto-generated method stub
        return goalDAO.getGoalsCompleted(goalid);
		
	}

	}

