package com.test.userInteraction;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.test.db.operations.BookManager;

public class UserInput {
	public int getUserInput() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		boolean isValidInput = false;
	    System.out.printf("Welcome. Select action: %d for READ, %d for CREATE, %d for UPDATE, %d for DELETE.", OperationOptions.READ.getValue(), OperationOptions.CREATE.getValue(), OperationOptions.UPDATE.getValue(), OperationOptions.DELETE.getValue());
	    while(!isValidInput)
		    try {		    	  
		    	choice = scanner.nextInt();
		    	isValidInput = true;
		    }
		    catch(InputMismatchException e) {
		    	 System.out.println("value must contain only number");
		    	 isValidInput = false;
		    	 scanner.nextLine();		    	 
		    }
	    
	    return choice;
	}

	public void processChoice(int userChoice, BookManager BookManager) {
		switch(userChoice) {
			case 0:
				System.out.println("Goodbye.");
				System.exit(0);
				break;
			case 1:
				System.out.println("You chose READ");
				break;
			case 2:
				System.out.println("You chose CREATE");
				break;
			case 3:
				System.out.println("You chose UPDATE");
				break;
			case 4:
				System.out.println("You chose DELETE");
				break;
				
		}
		
	}
}
