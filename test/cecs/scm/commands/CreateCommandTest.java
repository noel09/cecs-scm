package cecs.scm.commands;

import org.junit.Test;

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
