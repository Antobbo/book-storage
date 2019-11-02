package com.test.db.operations;

import org.hibernate.SessionFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import com.test.model.Book;
import com.test.model.Location;

public class BookManager {

	private SessionFactory sessionFactory;
	 
	public void setup() {
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
 
    public void exit() {
        // code to close Hibernate Session factory
    }
 
    public void create() {
        // code to save a book
    	Book book = new Book("Another test", "Jack", Location.DOWNSTAIRS.getValue());
    	Session session = sessionFactory.openSession();
	    session.beginTransaction();
	 
	    session.save(book);
	 
	    session.getTransaction().commit();
	    session.close();
    }
 
    public void read() {
        // code to get a book
    	Session session = sessionFactory.openSession();
    	String searchTerm = "Jack";
    	Criteria criteria = session.createCriteria(Book.class);
    	Book uniqueResult = (Book) criteria.add(Restrictions.eq("author", searchTerm)).uniqueResult();
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
//        EntityManager entitymanager = emfactory.createEntityManager( );
//        
//
//    	Query query = entitymanager.createQuery("SELECT id, title, author FROM books.books WHERE author = \"jack\";");
//    	List resultList = query.getResultList();
    }
 
    public void update() {
        // code to modify a book
    }
 
    public void delete() {
        // code to remove a book
    }
}
