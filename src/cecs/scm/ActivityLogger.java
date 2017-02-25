package cecs.scm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logs the activity to the manifest file
 */
public class ActivityLogger {
	
	private static String ACTIVITY_FOLDER_NAME = "activity";
	private static String MANIFEST_FILENAME = "manifest";
	
	private File manifest;
	
	/**
	 * Creates an activity folder in the root repository folder with a 
	 * manifest file with the datetime in the file name
	 * @param repoRootFolder
	 */
	public ActivityLogger(String repoRootFolder) {
		// make activity folder
		File activityFolder = new File(repoRootFolder + "\\" + ACTIVITY_FOLDER_NAME);
		activityFolder.mkdir();
		
		// make manifest
		SimpleDateFormat fmt = new SimpleDateFormat("YYYYMMdd_hhmm");
		String manifestFullName = MANIFEST_FILENAME + "_" + fmt.format(new Date());
		manifest = new File(activityFolder + "\\" + manifestFullName);
		
		System.out.println("Maniest file created: " + manifest);
		
		writeLine(manifest.getName());
		writeLine("Vix-1");
		
	}

	/**
	 * Write a line in the manifest file
	 * @param line
	 */
	public void writeLine(String line) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(manifest, true));
			writer.write(line + System.lineSeparator());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
