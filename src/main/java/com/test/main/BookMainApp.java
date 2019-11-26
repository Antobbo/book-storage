package com.test.main;

import com.test.db.operations.BookManager;
import com.test.userInteraction.UserInput;

public class BookMainApp {

	public static void main(String[] args) {
		BookMainApp bookMainApp = new BookMainApp();
		BookManager bookManager = new BookManager();
		UserInput userInput = new UserInput();
		bookManager.setup();
		//bookManager.create();
		//bookManager.read();
		//bookManager.update();
		//bookManager.delete();
		
		
		int userChoice = userInput.getUserInput();
		userInput.processChoice(userChoice, bookManager);
		bookManager.exit();
		

	}

}
