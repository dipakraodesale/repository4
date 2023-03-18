package com.Package.bookmgt;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/**
 * Hello world!
 *
 */
public class App
{
	public static SessionFactory createConfiguration() 
	{
		Configuration conf = new Configuration().configure().addAnnotatedClass(book.class);
		SessionFactory ss = conf.buildSessionFactory();
		return ss;
	}
	public static void createBook(Session ss, Transaction t) 
	{

		book b1 = new book(101, "prakash", 900,"vandana k","wilfried Library");
		book b2 = new book(102, "govindam",600, "panitha","american Library");
		book b3 = new book(103, "godan", 800, "premchand","mumabai Library");
		book b4 = new book(104, "lajpat", 600, "navnit","National Library");
		book b5 = new book(105, "vanprasth", 900, "aacharya","delhi Central Library");
		ss.save(b1);
		ss.save(b2);
		ss.save(b3);
		ss.save(b4);
		ss.save(b5);
		t.commit();
	}
	
	public static void getAllBook(Session ss, Transaction t) 
	{
		Query query = ss.createQuery("from book");

		List<book> books = query.list();
		for (book book : books) {
			System.out.println(book.getBookId()+book.getBookName()+book.getBookPrice()+book.getAuthorName()+book.getLibraryName());
		}
	}
	
	public static void updateBook(Session ss, Transaction t) 
	{
		t.begin();
		book b = ss.find(book.class, 2);
		b.setBookName("sambhaji");
		ss.save(b);
		t.commit();

	}
	
	public static void deleteBook(Session ss, Transaction t) 
	{
		t.begin();
		book b = ss.find(book.class, 2);

		ss.delete(b);
		t.commit();

	}
	
		public static void main(String[] args)

		{
			SessionFactory sessionfactory = createConfiguration();
			Session session = sessionfactory.openSession();
			Transaction t = session.beginTransaction();
			createBook(session, t);
			updateBook(session,t);
			deleteBook(session,t);
			getAllBook(session, t);
			System.out.println("done");
		}
	}

