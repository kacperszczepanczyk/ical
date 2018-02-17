package iCal;

import java.util.ArrayList;

public class VLessons {
	private ArrayList<VLesson> vlessons;

	public VLessons(ArrayList<Lesson> lessons) {
		vlessons = new ArrayList<VLesson>();
		for (int i = 0; i < lessons.size(); i++) {
			VLesson vlesson = new VLesson(lessons.get(i));
			vlessons.add(vlesson);
		}
	}
	
	public ArrayList<VLesson> getList(){
		return vlessons;
	}
}
