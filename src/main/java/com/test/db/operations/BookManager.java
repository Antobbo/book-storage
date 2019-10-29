package com.test.db.operations;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {

	private SessionFactory sessionFactory;
	 
	private void setup() {
        // code to load Hibernate Session factory
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} 
		catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(registry);
		}
    }
 
    private void exit() {
        // code to close Hibernate Session factory
    }
 
    private void create() {
        // code to save a book
    }
 
    private void read() {
        // code to get a book
    }
 
    private void update() {
        // code to modify a book
    }
 
    private void delete() {
        // code to remove a book
    }
}
