package com.test.userInteraction;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.test.db.operations.BookManager;

public class UserInput {
	private static final String READ = "READ";
	private static final String CREATE = "CREATE";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	private static final Scanner scanner = new Scanner(System.in);
	
	
	public int getUserInput() {
		//Scanner scanner = new Scanner(System.in);
		int choice = 0;
		boolean isValidInput = false;
	    System.out.printf("Welcome. Select action: %d for READ, %d for CREATE, %d for UPDATE, %d for DELETE.", OperationOptions.READ.getValue(), OperationOptions.CREATE.getValue(), OperationOptions.UPDATE.getValue(), OperationOptions.DELETE.getValue());
	    while(!isValidInput)
		    try {		    	  
		    	choice = scanner.nextInt();
		    	isValidInput = true;
		    	scanner.hasNextLine();
		    }
		    catch(InputMismatchException e) {
		    	 System.out.println("value must contain only number");
		    	 isValidInput = false;
		    	 scanner.nextLine();		    	 
		    }
	    return choice;
	}

	public void processChoice(int userChoice, BookManager bookManager) {
		String dbQuery = "";
		switch(userChoice) {
			case 0:
				System.out.println("Goodbye.");
				System.exit(0);
				break;
			case 1:
				System.out.println("You chose READ");
				dbQuery = getUserInputForProcessing(READ);
				bookManager.read(dbQuery);
				break;
			case 2:
				System.out.println("You chose CREATE");
				bookManager.create();
				break;
			case 3:
				System.out.println("You chose UPDATE");
				bookManager.update();
				break;
			case 4:
				System.out.println("You chose DELETE");
				dbQuery = getUserInputForProcessing(DELETE);

				bookManager.delete();
				break;
				
		}
		
	}
	
	public String getUserInputForProcessing(String action)
	{
		
		//Scanner scanner = new Scanner(System.in);
		System.out.printf("You chose %s: ", action);
		String nextLine = "";
		scanner.nextLine();
		
		if(action.equals(READ) || action.equals(DELETE) )
		{
			System.out.println("Enter the searching criterion");
		}
		try
		{
			nextLine = scanner.nextLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return nextLine;
	}
}
