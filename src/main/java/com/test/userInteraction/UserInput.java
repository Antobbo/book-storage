package com.test.userInteraction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.test.db.operations.BookManager;
import com.test.messages.Messages;

public class UserInput {
	
	private List<String> userInputs;
	private String dbField;
	private String newFieldValue;
	
	private static final String READ = "READ";
	private static final String CREATE = "CREATE";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	private static final String AUTHOR = "author";
	private static final String TITLE = "title";
	private static final String LOCATION = "location";
	private static final Scanner scanner = new Scanner(System.in);
	
	
	public int getUserInput() {
		int choice = 0;
		boolean isValidInput = false;
	    System.out.printf("Welcome. Select action: %d for READ, %d for CREATE, %d for UPDATE, %d for DELETE, %d for EXIT.", OperationOptions.READ.getValue(), OperationOptions.CREATE.getValue(), OperationOptions.UPDATE.getValue(), OperationOptions.DELETE.getValue(), OperationOptions.EXIT.getValue());
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
		switch(userChoice) {
			case 0:
				System.out.println("Goodbye.");
				System.exit(0);
				break;
			case 1:
				System.out.println("You chose READ");
				getUserInputForProcessing(READ);				
				bookManager.read(userInputs.get(0), dbField);				
				break;
			case 2:
				System.out.println("You chose CREATE");
				getUserInputForProcessing(CREATE);				
				bookManager.create(userInputs.get(0), userInputs.get(1), userInputs.get(2));							
				break;
			case 3:
				System.out.println("You chose UPDATE");
				getUserInputForProcessing(UPDATE);
				bookManager.update(userInputs.get(0), dbField, newFieldValue);
				break;
			case 4:
				System.out.println("You chose DELETE");
				getUserInputForProcessing(DELETE);				
				bookManager.delete(userInputs.get(0), dbField);				
				break;				
		}
		
	}
	
	public void getUserInputForProcessing(String action)
	{
		
		userInputs = new ArrayList<>();
		System.out.printf("You chose %s: ", action);
		String nextLine = "";
		scanner.nextLine();
		String searchCriterion = "";
		
		if(action.equals(READ) || action.equals(DELETE) )
		{
			userInputs.clear();
			System.out.println(Messages.RECORD_TO_RETRIEVE);

			nextLine = scanner.nextLine();
			 switch(nextLine)
			{
				case "1":
					System.out.println(Messages.ENTER_AUTHOR);
					searchCriterion = scanner.nextLine();
					break;
				case "2":
					System.out.println(Messages.ENTER_TITLE);
					searchCriterion = scanner.nextLine();
					break;
				case "3":
					System.out.println(Messages.ENTER_LOCATION);
					nextLine = scanner.nextLine();
					break;					
			}
			
			 userInputs.add(searchCriterion);
			 dbField = getDbField(nextLine);			  
		}
		
		else if(action.equals(CREATE))
		{
			userInputs.clear();
			try
			{
				System.out.println(Messages.ENTER_TITLE);
				String title = scanner.nextLine();
				System.out.println(Messages.ENTER_AUTHOR);
				String author = scanner.nextLine();
				System.out.println(Messages.ENTER_LOCATION);
				int location = scanner.nextInt();
				//todo: needs to validate the location
				userInputs.add(title);
				userInputs.add(author);
				userInputs.add(String.valueOf(location));
			}
			catch(Exception e)
			{
				
			}			
			
		}
		
		else if(action.equals(UPDATE))
		{
			userInputs.clear();
			System.out.println(Messages.FIELD_TO_UPDATE);
			String userChoice = scanner.nextLine();
			String fieldToAmend = "";
			
			switch(userChoice)
			{
				case "1":
					System.out.println(Messages.ENTER_AUTHOR);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_AUTHOR);
					newFieldValue = scanner.nextLine();
					break;
				case "2":
					System.out.println(Messages.ENTER_TITLE);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_TITLE);
					newFieldValue = scanner.nextLine();
					//todo: you need to enter the new value as well and store it in the array
					break;
				case "3":
					System.out.println(Messages.ENTER_LOCATION);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_LOCATION);
					newFieldValue = scanner.nextLine();
					//todo: you need to enter the new value as well and store it in the array
					break;					
			}
			
			userInputs.add(fieldToAmend);
			dbField = getDbField(userChoice);

		}		
	}
	
	private String getDbField(String choice)
	{
		switch(choice)
		{
		case "1":
			return AUTHOR;
		
		case "2":
			return TITLE;
		
		case "3":
			return LOCATION;
		}		
		return null;		
	}
}
