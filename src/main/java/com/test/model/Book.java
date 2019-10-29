package com.test.model;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	private long id;
	private String title;
	private String author;
	private Location location_id;	
	
	public Book(long id, String title, String author, Location location_id) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.location_id = location_id;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
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
		return location_id;
	}
	public void setLocation(Location location_id) {
		this.location_id = location_id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", location_id=" + location_id + "]";
	}

	
	
	
	
	
}
