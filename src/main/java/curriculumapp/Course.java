package curriculumapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Course {

	@Override
	public String toString() {
		return "Course [title=" + title + ", units=" + units + "]";
	}

	private String code;
	
	
	private List<String> courseObjectivesList  = new ArrayList<String>();
	
	public List<String> getCourseObjectivesList() {
		return courseObjectivesList;
	}

	public void setCourseObjectivesList(List<String> courseObjectivesList) {
		this.courseObjectivesList = courseObjectivesList;
	}
	
	public void addCourseObjective(String objective) {
		this.courseObjectivesList.add(objective);
	}

	public Course(String code, String title, String courseType) {
		super();
		this.code = code;
		this.title = title;
		this.courseType = courseType;
	}

	public Course() {
	}

	private String courseType;
	
	private List<String> labExercises;
	
	public List<String> getLabExercises() {
		return labExercises;
	}

	public void setLabExercises(List<String> labExercises) {
		this.labExercises = labExercises;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreditTitle() {
		return creditTitle;
	}

	public void setCreditTitle(String creditTitle) {
		this.creditTitle = creditTitle;
	}

	public String getCreditContent() {
		return creditContent;
	}

	public void setCreditContent(String creditContent) {
		this.creditContent = creditContent;
	}

	public String title;
	
	private String creditTitle;
	
	private String creditContent;
	
	public Map<String,String> units = new TreeMap<String,String>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getUnits() {
		return units;
	}

	public void setUnits(Map<String, String> units) {
		this.units = units;
	}
	
	public void addUnit(String unitName, String content) {
		this.units.put(unitName, content);
	}
	
	
}
