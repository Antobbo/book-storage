package com.test.db.operations;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.test.messages.Messages;
import com.test.model.Book;

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
    	Book book = new Book(title, author, Integer.parseInt(location));
    	Session session = sessionFactory.openSession();
	    session.beginTransaction();	 
	    session.save(book);	 
	    session.getTransaction().commit();
	    session.close();
    }
 
    public void read(String dbQuery, String field) {
        // code to get a book
    	Session session = sessionFactory.openSession();
    	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();    	
    	CriteriaQuery<Book> criteriaQuery  = criteriaBuilder.createQuery(Book.class);
    	Root<Book> bookRoot = criteriaQuery.from(Book.class);
    	
    	criteriaQuery.select(bookRoot);    	
    	criteriaQuery.where(criteriaBuilder.equal(bookRoot.get(field),dbQuery));
    	List<Book> resultList = session.createQuery(criteriaQuery).getResultList();
    	
    	if(resultList.isEmpty())
    	{
    		System.out.println(String.format(Messages.NO_RECORD_FOUND, dbQuery));
    	}
    	else
    	{
    		for(Book book : resultList)
    		{
    			System.out.println(book);
    		}    		
    	}
    	
    	String queryString = session.createQuery(criteriaQuery ).getQueryString();
    		
    }
 
    public void update(String dbQuery, String field, String modifiedField) {
        // code to modify a book
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();    	
    	CriteriaUpdate<Book> update = criteriaBuilder.createCriteriaUpdate(Book.class);
    	Root<Book> bookRoot = update.from(Book.class);
    	update.set(field, modifiedField);
    	update.where(criteriaBuilder.equal(bookRoot.get(field), dbQuery));
    	session.createQuery(update).executeUpdate();
    	session.getTransaction().commit();
	    session.close();
    }
 
    public void delete(String dbQuery, String field) {
        // code to remove a book
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();    	
    	CriteriaDelete<Book> delete = criteriaBuilder.createCriteriaDelete(Book.class);
    	Root<Book> bookRoot = delete.from(Book.class);
    	delete.where(criteriaBuilder.equal(bookRoot.get(field),dbQuery));
    	session.createQuery(delete).executeUpdate();
    	session.getTransaction().commit();
	    session.close();    	
    }  
}
