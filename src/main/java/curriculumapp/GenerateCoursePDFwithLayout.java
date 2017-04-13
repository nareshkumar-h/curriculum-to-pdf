package curriculumapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.Constants;

public class GenerateCoursePDFwithLayout {

	public static void main(String[] args) throws IOException {

		Document document = new Document(Constants.A4, 40, 60, 40, 60);
		createHeader(document);
		
		List<Course> courseDetails = CourseFileReader.getCourseListFromServer();
		for (Course course : courseDetails) {
			System.out.println(course.getCode() + "-" + course.getTitle() + "-" + course.getCourseType() + "-"
					+ course.getCourseType().equals("T"));
			if (course.getCourseType().equals("T") || course.getCourseType().equals("L")) {
				createPage(document, course);
			}
		}

		try {
			final OutputStream outputStream = new FileOutputStream(
					"C:\\Users\\Mvision\\Desktop\\VELS_Revature_Curriculum.pdf");
			document.save(outputStream);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createHeader(Document document) throws IOException {
		String degreeTitle = "BE-Computer Science (Enterprise Programming)";
		Paragraph cparagraph = new Paragraph();
		cparagraph.setAlignment(Alignment.Center);
		cparagraph.addText(degreeTitle, 18, PDType1Font.TIMES_BOLD);

		document.add(cparagraph);
		
	}
	
	private static void addCourseObjectives(Document document, Course course) throws IOException {
		String degreeTitle = "Course Objectives:";
		
		List<String> courseObjectivesList = course.getCourseObjectivesList();
		System.out.println("Objectives:"  +  courseObjectivesList);
		if (! courseObjectivesList.isEmpty()) {
		
			Paragraph cparagraph = new Paragraph();
			cparagraph.setAlignment(Alignment.Center);
			cparagraph.addText(degreeTitle, 12, PDType1Font.TIMES_BOLD);

			
			document.add(cparagraph);
			
			int i = 1;
			for (String task : courseObjectivesList) {

				String objectiveName = "" + i++ + ". " + task;

				Paragraph tparagraph = new Paragraph();
				tparagraph.setLineSpacing(1.5f);
				tparagraph.addText(objectiveName, 10, PDType1Font.TIMES_ROMAN);
		
				cparagraph.setLineSpacing(2);
				document.add(tparagraph);

			}

		}
				
	}

	private static void createPage(Document document, Course course) throws IOException {

		Course courseDetails = CourseFileReader.getCourseDetails(course, course.getCode() + ".json");
		System.out.println(courseDetails.getTitle());
		Paragraph cparagraph = new Paragraph();
		cparagraph.addText(courseDetails.getCode() + "-" + courseDetails.getTitle(), 15, PDType1Font.TIMES_BOLD);

		document.add(cparagraph);

		if (course.getCourseType().equals("T")) {

			addCourseObjectives(document, courseDetails);
			cparagraph.setLineSpacing(2);
			createUnitSection(document, courseDetails);
		} else if (course.getCourseType().equals("L")) {
			createLabSection(document, courseDetails);
		}

		cparagraph.setLineSpacing(5);
	}

	private static void createUnitSection(Document document, Course courseDetails) throws IOException {
		Map<String, String> units = courseDetails.getUnits();
		int i = 1;
		for (String unitName : units.keySet()) {

			String moduleName = "UNIT " + i++ + " :" + unitName;

			Paragraph tparagraph = new Paragraph();
			tparagraph.setLineSpacing(2f);
			tparagraph.addText(moduleName, 12, PDType1Font.TIMES_BOLD);
			document.add(tparagraph);

			String topicsContent = units.get(unitName);

			Paragraph paragraph = new Paragraph();
			paragraph.setLineSpacing(1.5f);
			paragraph.addText(topicsContent, 10, PDType1Font.TIMES_ROMAN);
			document.add(paragraph);

		}
	}

	private static void createLabSection(Document document, Course courseDetails) throws IOException {
		List<String> labExercises = courseDetails.getLabExercises();
		int i = 1;
		for (String task : labExercises) {

			String moduleName = "" + i++ + ". " + task;

			Paragraph tparagraph = new Paragraph();
			tparagraph.setLineSpacing(1.5f);
			tparagraph.addText(moduleName, 10, PDType1Font.TIMES_ROMAN);
			document.add(tparagraph);

		}
	}
}
