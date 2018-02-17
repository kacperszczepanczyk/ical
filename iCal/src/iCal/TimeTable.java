package iCal;
import java.util.ArrayList;

public class TimeTable {
	private ArrayList <Lesson> lessonList;
	
	
	public TimeTable() {
		super();
		this.lessonList = new ArrayList<>();
	}

	public void addLessonToList (Lesson lesson){
		lessonList.add(lesson);
	}
	
	public ArrayList<Lesson> getLessonList() {
		return lessonList;
	}

	public void setLessonList(ArrayList<Lesson> lessonList) {
		this.lessonList = lessonList;
	}
}
