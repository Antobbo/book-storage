package com.test.userInteraction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.test.db.operations.BookManager;
import com.test.messages.Messages;

public class UserInput {
	private static final String READ = "READ";
	private static final String CREATE = "CREATE";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	private static final int RETRIEVE_OR_DELETE_QUERY_NUMBER_ARGS = 1;
	private static final int CREATE_QUERY_NUMBER_ARGS = 3;
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
		List<String> dbQuery = new ArrayList<>();
		switch(userChoice) {
			case 0:
				System.out.println("Goodbye.");
				System.exit(0);
				break;
			case 1:
				System.out.println("You chose READ");
				dbQuery = getUserInputForProcessing(READ);
				if(dbQuery.size() == RETRIEVE_OR_DELETE_QUERY_NUMBER_ARGS)
				{
					bookManager.read(dbQuery.get(0));
				}
				break;
			case 2:
				System.out.println("You chose CREATE");
				dbQuery = getUserInputForProcessing(CREATE);
				if(dbQuery.size() == CREATE_QUERY_NUMBER_ARGS)
				{
					bookManager.create(dbQuery.get(0), dbQuery.get(1), dbQuery.get(2));
				}
				else
				{
					System.out.println(Messages.WRONG_NUMBER_OF_ARGUMENTS);
				}
				
				break;
			case 3:
				System.out.println("You chose UPDATE");
				dbQuery = getUserInputForProcessing(UPDATE);
				bookManager.update(dbQuery.get(0), dbQuery.get(1), dbQuery.get(2));
				break;
			case 4:
				System.out.println("You chose DELETE");
				dbQuery = getUserInputForProcessing(DELETE);
				if(dbQuery.size() == RETRIEVE_OR_DELETE_QUERY_NUMBER_ARGS)
				{
					bookManager.delete(dbQuery.get(0));
				}
				else
				{
					System.out.println(Messages.WRONG_NUMBER_OF_ARGUMENTS);
				}
			//	bookManager.delete();
				break;
				
		}
		
	}
	
	public List<String> getUserInputForProcessing(String action)
	{
		
		List<String> userInputs = new ArrayList<>();
		System.out.printf("You chose %s: ", action);
		String nextLine = "";
		scanner.nextLine();
		String searchCriterion = "";
		
		if(action.equals(READ) || action.equals(DELETE) )
		{
			userInputs.clear();
			System.out.println(Messages.RECORD_TO_RETRIEVE);
//			try
//			{
//				nextLine = scanner.nextLine();
//				userInputs.add(nextLine);
//				return userInputs;
//				
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
			
			
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
			 userInputs.add(nextLine);
			  
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
				return userInputs;
			}
			catch(Exception e)
			{
				
			}			
			
		}
		
		else if(action.equals(UPDATE))
		{
			userInputs.clear();
			System.out.println(Messages.FIELD_TO_UPDATE);
			int userChoice = scanner.nextInt();
			String fieldToAmend = "";
			String newValue = "";
			scanner.nextLine();
			
			switch(userChoice)
			{
				case 1:
					System.out.println(Messages.ENTER_AUTHOR);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_AUTHOR);
					newValue = scanner.nextLine();
					break;
				case 2:
					System.out.println(Messages.ENTER_TITLE);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_TITLE);
					newValue = scanner.nextLine();
					//todo: you need to enter the new value as well and store it in the array
					break;
				case 3:
					System.out.println(Messages.ENTER_LOCATION);
					fieldToAmend = scanner.nextLine();
					System.out.println(Messages.ENTER_MODIFIED_LOCATION);
					newValue = scanner.nextLine();
					//todo: you need to enter the new value as well and store it in the array
					break;					
			}
			
			userInputs.add(fieldToAmend);
			userInputs.add(String.valueOf(userChoice));
			userInputs.add(newValue);
			return userInputs;
		}
		return null;

		
	}
}
