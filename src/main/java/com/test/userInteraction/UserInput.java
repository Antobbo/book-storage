package com.test.userInteraction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.test.db.operations.BookManager;
import com.test.messages.Messages;
import com.test.model.Location;

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
		    	if(choice == OperationOptions.READ.getValue() || choice == OperationOptions.CREATE.getValue() || choice == OperationOptions.UPDATE.getValue() || choice == OperationOptions.DELETE.getValue() || choice == OperationOptions.EXIT.getValue())
		    	{
		    		isValidInput = true;
		    	}
		    	else
		    	{
		    		isValidInput = false;
		    		System.out.println(String.format(Messages.NOT_ALLOWED_VALUES, OperationOptions.READ.getValue(), OperationOptions.CREATE.getValue(), OperationOptions.UPDATE.getValue(), OperationOptions.DELETE.getValue(), OperationOptions.EXIT.getValue()));
		    	}
		    	
		    	scanner.hasNextLine();
		    }
		    catch(InputMismatchException e) {
		    	 System.out.println(Messages.NOT_NUMERICAL_ERROR);
		    	 isValidInput = false;
		    	 scanner.nextLine();		    	 
		    }
	    return choice;
	}

	public void processChoice(int userChoice, BookManager bookManager) {
		
		switch(userChoice)
		{
			case 0:
				System.out.println("Goodbye.");
				System.exit(0);
				break;
			case 1:
				System.out.println("You chose READ");
				getUserInputForProcessing(READ);
				if(!userInputs.isEmpty())
				{
					bookManager.read(userInputs.get(0), dbField);
				}
				break;
			case 2:
				System.out.println("You chose CREATE");
				getUserInputForProcessing(CREATE);	
				if(!userInputs.isEmpty())
				{
					bookManager.create(userInputs.get(0), userInputs.get(1), userInputs.get(2));	
				}
				break;
			case 3:
				System.out.println("You chose UPDATE");
				getUserInputForProcessing(UPDATE);
				if(!userInputs.isEmpty())
				{
					bookManager.update(userInputs.get(0), dbField, newFieldValue);
				}
				break;
			case 4:
				System.out.println("You chose DELETE");
				getUserInputForProcessing(DELETE);	
				if(!userInputs.isEmpty())
				{
					bookManager.delete(userInputs.get(0), dbField);	
				}
				break;	
			default:
				System.out.println(Messages.WRONG_ACTION_PROGRAM_TERMINATED);
				
		}
		
	}
	
	public void getUserInputForProcessing(String action)
	{
		
		userInputs = new ArrayList<>();
		System.out.printf("You chose %s: ", action);
		String userInput = "";
		scanner.nextLine();
		String searchCriterion = "";
		
		
		if(action.equals(READ) || action.equals(DELETE) )
		{
			boolean isValid = false;
			userInputs.clear();
			System.out.println(Messages.RECORD_TO_RETRIEVE);

			userInput = scanner.nextLine();
			while(!isValid)
			{			
				switch(userInput)
				{
					case "1":
						System.out.println(Messages.ENTER_AUTHOR);
						searchCriterion = scanner.nextLine();
						isValid = true;
						break;
					case "2":
						System.out.println(Messages.ENTER_TITLE);
						searchCriterion = scanner.nextLine();
						isValid = true;
						break;
					case "3":
						System.out.println(Messages.ENTER_LOCATION);
						searchCriterion = scanner.nextLine();
						isValid = isInputValid(searchCriterion);
						if(!isValid)
						{
							Location[] values = Location.values();
							System.out.println(String.format(Messages.INVALID_LOCATION, values[0].getValue(), Location.values().length));
						}
						break;	
					default:
						System.out.println(Messages.WRONG_ACTION_TRY_AGAIN);
						System.out.println(Messages.RECORD_TO_RETRIEVE);
						userInput = scanner.nextLine();
						break;
				}				
					
			}
			
			 userInputs.add(searchCriterion);
			 dbField = getDbField(userInput);
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
			boolean isValid = false;
			userInputs.clear();
			System.out.println(Messages.FIELD_TO_UPDATE);
			String userChoice = scanner.nextLine();
			String fieldToAmend = "";
			while(!isValid) {
				switch(userChoice)
				{
					case "1":
						System.out.println(Messages.ENTER_AUTHOR);
						fieldToAmend = scanner.nextLine();
						System.out.println(Messages.ENTER_MODIFIED_AUTHOR);
						newFieldValue = scanner.nextLine();
						isValid = true;
						break;
					case "2":
						System.out.println(Messages.ENTER_TITLE);
						fieldToAmend = scanner.nextLine();
						System.out.println(Messages.ENTER_MODIFIED_TITLE);
						newFieldValue = scanner.nextLine();
						isValid = true;
						//todo: you need to enter the new value as well and store it in the array
						break;
					case "3":
						System.out.println(Messages.ENTER_LOCATION);
						fieldToAmend = scanner.nextLine();
						System.out.println(Messages.ENTER_MODIFIED_LOCATION);
						newFieldValue = scanner.nextLine();
						isValid = true;
						//todo: you need to enter the new value as well and store it in the array
						break;
					default:
						System.out.println(Messages.WRONG_ACTION_TRY_AGAIN);
						System.out.println(Messages.RECORD_TO_RETRIEVE);
						userChoice = scanner.nextLine();
						break;
				}
			}
			userInputs.add(fieldToAmend);
			dbField = getDbField(userChoice);

		}		
	}
	
	private boolean isInputValid(String searchCriterion) {
		return searchCriterion.equals(String.valueOf(Location.DOWNSTAIRS.getValue())) || 
				searchCriterion.equals(String.valueOf(Location.UPSTAIRS_FIRST_BEDROOM.getValue())) || 
				searchCriterion.equals(String.valueOf(Location.UPSTAIRS_SECOND_BEDROOM.getValue())) || 
				searchCriterion.equals(String.valueOf(Location.UPSTAIRS_SPARE_ROOM.getValue())) || 
				searchCriterion.equals(String.valueOf(Location.ON_LOAN.getValue()));
		
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
			
		default:
			return null;
		}		
	}
}
