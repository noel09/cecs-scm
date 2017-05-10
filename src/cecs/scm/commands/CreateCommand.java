package cecs.scm.commands;

import java.io.File;
import java.io.IOException;

import cecs.scm.ActivityLogger;
import cecs.scm.ArtifactGenerator;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 * 
 * FILE DESCRIPTION:
 * Command to create a new repository from an existing source.
 */
public class CreateCommand extends CopyFile implements Command{
	/**
	 * @param srcFolder: Root directory of the source
	 * @param repoFolder: Root directory of the repository
	 */
	public CreateCommand(String srcFolder, String repoFolder) {
		super(srcFolder, repoFolder);
	}
	
	@Override
	public void execute() {
		CloneFiles("create");
	}
}
