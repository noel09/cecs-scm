package cecs.scm;

import java.util.Scanner;

import cecs.scm.commands.Command;
import cecs.scm.commands.CreateCommand;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 * 
 * FILE DESCRIPTION:
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
				if (args.length == 3) {
					command = new CreateCommand(args[1], args[2]);
					command.execute();
				}
				else {
					System.out.println("Invalid params.");
				}

			} else if ("exit".equals(args[0])) { // Exit case
				break;
				
			} else { // Incorrect input
				System.out.println("Invalid command.");
			}
		}
		sc.close();
	}
}
