package cecs.scm.commands;

import org.junit.Test;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 * 
 * FILE DESCRIPTION:
 * Tests the create repository command
 */
public class CreateCommandTest {

	private String workingDir = "C:\\Users\\Douglas\\Workspace\\School\\CECS543\\cecs-scm\\samples";
	
	@Test
	public void executemyptTest() {
		String srcFolder = workingDir + "\\mypt";
		String repoFolder = workingDir + "\\mypt_repo";
		CreateCommand command = new CreateCommand(srcFolder, repoFolder);
		command.execute();
	}

	@Test
	public void executemypt2Test() {
		String srcFolder = workingDir + "\\mypt2";
		String repoFolder = workingDir + "\\mypt2_repo";
		CreateCommand command = new CreateCommand(srcFolder, repoFolder);
		command.execute();
	}
	
	@Test
	public void executemypt3Test() {
		String srcFolder = workingDir + "\\mypt3";
		String repoFolder = workingDir + "\\mypt3_repo";
		CreateCommand command = new CreateCommand(srcFolder, repoFolder);
		command.execute();
	}
}
