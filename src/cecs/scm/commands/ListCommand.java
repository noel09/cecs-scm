package cecs.scm.commands;


import java.io.File;
import java.io.FileFilter;

import cecs.scm.ActivityLogger;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 *
 * FILE DESCRIPTION:
 * Command to list versions in the repository.
 */
public class ListCommand implements Command {

	private String repoFolder;
	
	/**
	 * @param repoFolder: Root directory of the repository
	 */
	public ListCommand(String repoFolder) {
		this.repoFolder = repoFolder;
	}
	
	@Override
	public void execute() {
		File folder = new File(repoFolder + File.separator + ActivityLogger.ACTIVITY_FOLDER_NAME);
		
		// return all files that start with "manifest_"
		File[] manifestFiles = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().startsWith(ActivityLogger.MANIFEST_FILENAME);
			}
		});
		
		for(int i = manifestFiles.length - 1; i >= 0; i--) {
			File f = manifestFiles[i];
			String versionName = f.getName().substring(ActivityLogger.MANIFEST_FILENAME.length() + 1);
			System.out.println(versionName);
		}
	}
}
