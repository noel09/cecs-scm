package cecs.scm;

import java.util.Scanner;

import cecs.scm.commands.*;

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
			
			// TODO: For error handling, we probably want avoid 
			// repeating error outputs. Consider using Exceptions?
			if ("create".equalsIgnoreCase(args[0])) { // Create repo
				if (args.length == 3) {
					command = new CreateCommand(args[1], args[2]);
					command.execute();
				} else {
					System.out.println("Invalid params.");
				}
			} else if("checkin".equalsIgnoreCase(args[0])){
				if (args.length == 3) {
					command = new CheckinCommand(args[1], args[2]);
					command.execute();
				} else {
					System.out.println("Invalid params.");
				}
			}
			else if ("checkout".equalsIgnoreCase(args[0])) { // Checkout
				if (args.length == 4) {
					command = new CheckoutCommand(args[1], args[2], args[3]);
					command.execute();
				} else {
					System.out.println("Invalid params.");
				}
			} else if ("list".equalsIgnoreCase(args[0])) { // List versions
				if (args.length == 2) {
					command = new ListCommand(args[1]);
					command.execute();
				} else {
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
