package iCal;
import java.util.ArrayList;
import java.util.List;

public class Lesson {
	private String subGroup;
	private String startTime;
	private String endTime;
	private String classType;
	private String subject;
	private String teacher;
	private String classRoom;
	private List <String> dueDatesList = new ArrayList<>();
	private String infoAboutDueDates;
	private String dayOfWeek;

	public Lesson(){};
	
	@Override
	public String toString() {
		return "Lesson [subGroup=" + subGroup
				+ ", startTime=" + startTime
				+ ", endTime=" + endTime
				+ ", classType=" + classType
				+ ", subject=" + subject
				+ ", teacher=" + teacher
				+ ", classRoom=" + classRoom
				+ ", dueDatesList=" + dueDatesList 
				+ ", dayOfWeek=" + dayOfWeek
				+ ", infoAboutDueDates=" + infoAboutDueDates
				+ "]";
	}

	
	public String getSubGroup() {
		return subGroup;
	}
	
	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public String getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	
	public List<String> getTerm() {
		return dueDatesList;
	}
	
	public void setTerm(List<String> term) {
		this.dueDatesList = term;
	}

	public List<String> getDueDate() {
		return dueDatesList;
	}

	public void setDueDate(List<String> dueDate) {
		this.dueDatesList = dueDate;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public List<String> getDueDatesList() {
		return dueDatesList;
	}
	
	public void setDueDatesList(List<String> dueDatesList) {
		this.dueDatesList = dueDatesList;
	}
	
	public String getInfoAboutDueDates() {
		return infoAboutDueDates;
	}
	
	public void setInfoAboutDueDates(String infoAboutDueDates) {
		this.infoAboutDueDates = infoAboutDueDates;
	}
}