package curriculumapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseFileReader {

	public static void main(String[] args) {
		Course c = new Course();
		Course courseDetails = CourseFileReader.getCourseDetails(c, "C301.json");
		// System.out.println(courseDetails);

	}

	public static List<Course> getCourseList() {

		List<Course> courseList = new ArrayList<Course>();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("json/courses.json"));

			// convert Object to JSONObject
			JSONArray courseListObject = (JSONArray) object;

			for (Object obj : courseListObject) {
				JSONObject courseObj = (JSONObject) obj;
				// Reading the String
				String title = (String) courseObj.get("title");
				String code = (String) courseObj.get("code");
				String courseType = (String) courseObj.get("type");
				Course course = new Course(code, title, courseType);
				courseList.add(course);
			}

		} catch (Exception e) {

		}

		return courseList;

	}
	
	public static List<Course> getCourseListFromServer() {

		List<Course> courseList = new ArrayList<Course>();
		JSONParser parser = new JSONParser();
		try {
			String courseJSON  = FileUtil.getContent("courses.json");
			Object object = parser.parse(courseJSON);

			// convert Object to JSONObject
			JSONArray courseListObject = (JSONArray) object;

			for (Object obj : courseListObject) {
				JSONObject courseObj = (JSONObject) obj;
				// Reading the String
				String title = (String) courseObj.get("title");
				String code = (String) courseObj.get("code");
				String courseType = (String) courseObj.get("type");
				
				Course course = new Course(code, title, courseType);
				courseList.add(course);
			}

		} catch (Exception e) {

		}

		return courseList;

	}

	public static Course getCourseDetails(Course course, String courseFileName) {

		// Course course = new Course();

		JSONParser parser = new JSONParser();
		try {
			String courseJSON  = FileUtil.getContent(courseFileName);
			Object object = parser.parse(courseJSON);

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// Reading the String
			String title = (String) jsonObject.get("title");
			// String code = (String) jsonObject.get("code");

			// Reading the array

			//course.setTitle(title);
			// course.setCode(code);

			//// System.out.println(title);
			if (course.getCourseType().equals("T")) {
				JSONArray modules = (JSONArray) jsonObject.get("modules");
				JSONArray courseObjectives = (JSONArray) jsonObject.get("course_objectives");
				if (courseObjectives != null ){
					
					for (Object c : courseObjectives) {
						course.addCourseObjective(c.toString());
					}
				}
				
				setUnits(course, modules);
			}else if (course.getCourseType().equals("L")) {
				JSONArray tasks = (JSONArray) jsonObject.get("tasks");
				List<String> taskList = new ArrayList<String>();
				if (tasks != null) {
					for (Object obj : tasks) {
						JSONObject task = (JSONObject) obj;
						String taskName = (String)task.get("name");
						taskList.add(taskName);
					}
				}
				course.setLabExercises(taskList);

			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	private static void setUnits(Course course, JSONArray modules) {
		for (Object module : modules) {
			JSONObject moduleObj = (JSONObject) module;
			String unitName = (String) moduleObj.get("name");
			JSONArray topics = (JSONArray) moduleObj.get("topics");
			// System.out.println(unitName);
			StringBuilder content = new StringBuilder();
			for (Object topic : topics) {
				// System.out.println(topic);
				content.append(topic + " - ");
			}
			String topicsContent = content.toString().substring(0, content.lastIndexOf("-") - 1);
			course.addUnit(unitName, topicsContent);
		}
	}
}
