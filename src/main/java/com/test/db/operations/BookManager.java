package com.test.db.operations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import com.test.messages.Messages;
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
 
    public void create(String title, String author, String location) {
        // code to save a book
    	
    	//TODO: parse location
    	Book book = new Book(title, author, Integer.parseInt(location));
    	Session session = sessionFactory.openSession();
	    session.beginTransaction();
	 
	    session.save(book);
	 
	    session.getTransaction().commit();
	    session.close();
    }
 
    public void read(String dbQuery) {
        // code to get a book
    	Session session = sessionFactory.openSession();
    	Criteria criteria = session.createCriteria(Book.class);
    	Book uniqueResult = (Book) criteria.add(Restrictions.eq("author", dbQuery)).uniqueResult();
    	if(uniqueResult != null)
    	{
    		//System.out.println("Result is: " + uniqueResult);
    		String message = String.format(Messages.RECORD_FOUND, dbQuery, uniqueResult);
    		System.out.println(message);
    	}
    	else
    	{
    		System.out.println(String.format(Messages.NO_RECORD_FOUND, dbQuery));
    	}
    	
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
//        EntityManager entitymanager = emfactory.createEntityManager( );
//        
//
//    	Query query = entitymanager.createQuery("SELECT id, title, author FROM books.books WHERE author = \"jack\";");
//    	List resultList = query.getResultList();
    }
 
    public void update() {
        // code to modify a book
    	Session session = sessionFactory.openSession();
    	String searchTerm = "Jack";
    	session.beginTransaction();
    	Criteria criteria = session.createCriteria(Book.class);
    	Book uniqueResult = (Book) criteria.add(Restrictions.eq("author", searchTerm)).uniqueResult();
    	uniqueResult.setTitle("Amended plus one");
    	session.update(uniqueResult);
    	session.getTransaction().commit();
	    session.close();
	    System.out.println(uniqueResult);
    	
    }
 
    public void delete(String dbQuery) {
        // code to remove a book
    	Session session = sessionFactory.openSession();
    	//String searchTerm = "Jack";
    	session.beginTransaction();
    	Criteria criteria = session.createCriteria(Book.class);
    	Book uniqueResult = (Book) criteria.add(Restrictions.eq("author", dbQuery)).uniqueResult();
    	session.delete(uniqueResult);
    	session.getTransaction().commit();
	    session.close();
    }
    
}
