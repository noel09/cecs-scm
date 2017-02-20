package cecs.scm;

import java.util.Scanner;

import cecs.scm.commands.Command;
import cecs.scm.commands.CreateCommand;

/**
 * The console user interface. Handles the user's inputs.
 */
public class SCMConsole {

	public SCMConsole() {
		initialize();
	}
	
	/**
	 * Initialize a loop that continously accepts 
	 * user's inputs
	 */
	private void initialize() {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
		
			String line = sc.nextLine();
			String[] args = line.split(" ");
			
			// TODO: need to check user's inputs
			
			Command command;
			
			if ("create".equals(args[0])) { // Create repo
				if (args.length != 3) { continue; }
				command = new CreateCommand(args[1], args[2]);
				
			} else if ("exit".equals(args[0])) { // Exit case
				break;
				
			} else { // Incorrect input
				continue;
			}
			
			command.execute();
		}
		sc.close();
	}
}
