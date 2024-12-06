package model;

import java.time.LocalDate;
import java.util.Date;

public class GoalPlan {


	private String goalid;
    private int planid;
    private String goaldescription;
    
    private LocalDate duedate;
    private String iscompleted;	
    private String remarks;
	public GoalPlan() {
		super();
	}
	public GoalPlan(String goalid, int planid, String goaldescription, LocalDate duedate, String iscompleted,String remarks) {
		super();
		this.goalid = goalid;
		this.planid = planid;
		this.goaldescription = goaldescription;
		this.duedate = duedate;
		this.iscompleted = iscompleted;
		this.remarks = remarks;
	}
	public String getGoalid() {
		return goalid;
	}
	public void setGoalid(String goalid) {
		this.goalid = goalid;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	public String getGoaldescription() {
		return goaldescription;
	}
	public void setGoaldescription(String goaldescription) {
		this.goaldescription = goaldescription;
	}
	public LocalDate getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}
	public String getIscompleted() {
		return iscompleted;
	}
	public void setIscompleted(String iscompleted) {
		this.iscompleted = iscompleted;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
	
    
}
