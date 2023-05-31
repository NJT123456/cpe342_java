package class5_2;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateNewFile {
	public static void main(String[] args) throws Exception {
		// create a file object for the current location
		File file = new File("f.txt");
		// create a new file with name specified
		// by the file object
		boolean value = file.createNewFile();
		if (value) {
			System.out.println("New File is created.");
		} else {
			System.out.println("The file already exists.");
		}

	  }
}
