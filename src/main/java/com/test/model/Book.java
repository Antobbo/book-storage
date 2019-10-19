package com.test.model;

public class Book {

	private String title;
	private String author;
	private Location location;	
	
	public Book(String title, String author, Location location) {
		this.title = title;
		this.author = author;
		this.location = location;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", location=" + location + "]";
	}
	
	
	
	
}
