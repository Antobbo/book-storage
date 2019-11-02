package com.test.model;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	private long id;
	private String title;
	private String author;
	private int location_id;	
	
	public Book(String title, String author, int location_id) {
		this.title = title;
		this.author = author;
		this.location_id = location_id;
	}
	public Book()
	{
		
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
	
    @Column(name = "title")
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name = "location_id")
	public int getLocation() {
		return location_id;
	}
	public void setLocation(int location_id) {
		this.location_id = location_id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", location_id=" + location_id + "]";
	}

	
	
	
	
	
}
