package model;

import java.time.LocalDate;
import java.util.Date;

public class StudyPlan {

	private int planid;
    private String planname;
    private String plandescription;
    
    private LocalDate startdate;
    private LocalDate enddate;	
    private String subjectid;
    
	public StudyPlan() {
		super();
	}
	public StudyPlan(int planid, String planname, String plandescription, LocalDate startdate, LocalDate enddate,
			String subjectid) {
		super();
		this.planid = planid;
		this.planname = planname;
		this.plandescription = plandescription;
		this.startdate = startdate;
		this.enddate = enddate;
		this.subjectid = subjectid;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	public String getPlanname() {
		return planname;
	}
	public void setPlanname(String planname) {
		this.planname = planname;
	}
	public String getPlandescription() {
		return plandescription;
	}
	public void setPlandescription(String plandescription) {
		this.plandescription = plandescription;
	}
	public LocalDate getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDate startdate) {
		this.startdate =startdate ;
	}
	public LocalDate getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	
    
}
