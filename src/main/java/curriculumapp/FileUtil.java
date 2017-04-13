package curriculumapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtil {

	public static void main(String[] args) throws IOException {

		String filename = "courses.json";
		String content = getContent(filename);
		System.out.println(content);
	}

	public static String getContent(String filename) throws MalformedURLException, IOException {

		StringBuilder content = new StringBuilder();
		//URL url = new URL("https://nareshkumar-h.github.io/revature-curriculum/json/" + filename);
		URL url = new URL("http://localhost/revature-curriculum/json/" + filename);

		// Read all the text returned by the server
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String str;
		while ((str = in.readLine()) != null) {
			content.append(str);
		}
		in.close();

		return content.toString();
	}
}
