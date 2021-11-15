package vn.devpro.btck.update;

public class Mark {
	
	private String studentID, subjectID;
	private double mark;
	
	public Mark() {}
	public Mark(String studentID, String subjectID, double mark) {
		this.studentID = studentID;
		this.subjectID = subjectID;
		this.mark = mark;
	}
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
}
