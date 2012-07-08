package br.com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.tables.Event;
import br.com.tables.MockObject;


public class Conexao {

	public Connection conectar() throws Exception{
		Connection conn = null;
		try{
			Class.forName("org.hsqldb.jdbcDriver");
		}
		catch(Exception e){
			System.out.println("Erro ao carregar o driver JDBC. ");
			JOptionPane.showMessageDialog(null, "Erro ao carregar o driver JDBC. ");
		}
		//JOptionPane.showMessageDialog(null, "dir:"+System.getProperty("user.dir"));
		//JOptionPane.showMessageDialog(null, "classe:"+getClass().getResource(".").getPath());
		System.out.println(getClass().getResource(".").getPath());
		conn = DriverManager.getConnection("jdbc:hsqldb:file:./data/contratos;shutdown=true", "sa", "");
		return conn;
	}
	
	public static Connection getConnection(){
		
		 SessionFactory sf = new Configuration().configure().buildSessionFactory();  
	      Session s = sf.openSession();  
	      Transaction tx = s.beginTransaction();
	      
	      EntityManager gete;
		return null;  
	}
	
	 private void createAndStoreEvent(String title, Date theDate) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();

	        Event theEvent = new Event();
	        theEvent.setTitle(title);
	        theEvent.setDate(theDate);
	        session.save(theEvent);

	        session.getTransaction().commit();
	        
	     //   session.close();
	    }
	 
	 private void createMock(){
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        Transaction transaction = session.beginTransaction();
	 
	        MockObject object0 = new MockObject();
	        MockObject object1 = new MockObject();
	        session.save(object0);
	        session.save(object1);
	 
	        transaction.commit();
	 
	        System.out.println("Object 0");
	        System.out.println("Generated ID is: " + object0.getId());
	        System.out.println("Generated Version is: " + object0.getVersion());
	 
	        System.out.println("Object 1");
	        System.out.println("Generated ID is: " + object1.getId());
	        System.out.println("Generated Version is: " + object1.getVersion());
	        
	       // session.close();
	 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Conexao a = new Conexao();
		 
		 try {
			//conexão JDBC 
			Connection b = a.conectar();
			if(b != null){
				System.out.println("conectado");
				JOptionPane.showMessageDialog(null, "conectado");
				
				/*
			    SessionFactory sf = new Configuration().configure().buildSessionFactory();  
			      Session s = sf.openSession();  
			      Transaction tx = s.beginTransaction();  
			  
			      Aluno a = new Aluno();  
			      a.setId(1);  
			      a.setNome("Urso");  
			      a.setEmail("comedia@hibernate.org");  
			      a.setCurso("Informatica");  
			      s.save(a);  
			      tx.commit();  
			      s.close();  */
				
				//conexão Hibernate
				System.out.println(1);
				Configuration conf = new AnnotationConfiguration();  
		        conf.configure();  
		        SessionFactory factory = conf.buildSessionFactory();  
		        Session session = factory.openSession(); 
		        
		        Session session2 = HibernateUtil.getSession();  
	            session2.beginTransaction();  
				
				
				a.createAndStoreEvent("titulo 1", new Date());
				a.createMock();
				b.commit();
				b.close();
				System.exit(0);
			}else{
				System.out.println("não conectado");
				JOptionPane.showMessageDialog(null, "não conectado");
				System.exit(0);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
