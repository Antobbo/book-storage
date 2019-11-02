package com.test.main;

import com.test.db.operations.BookManager;

public class BookMainApp {

	public static void main(String[] args) {
		BookMainApp bookMainApp = new BookMainApp();
		BookManager bookManager = new BookManager();
		bookManager.setup();
		//bookManager.create();
		//bookManager.read();
		//bookManager.update();
		//bookManager.delete();
		bookManager.exit();
		

	}

}
