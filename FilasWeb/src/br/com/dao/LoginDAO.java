package br.com.dao;

import java.util.List;

import org.hibernate.*;

import br.com.config.HibernateUtil;
import br.com.tables.Usuario;

public class LoginDAO {

	private SessionFactory factory;

	public LoginDAO() throws Exception{  
		//factory = new Configuration().addClass(Usuario.class).buildSessionFactory();
		factory =  (SessionFactory) HibernateUtil.getSessionFactory().getCurrentSession();
	}       

	public void insert(Usuario amigo) throws Exception{  
		Session session = factory.openSession();  
		session.save(amigo);  
		session.flush();  
		session.close();  
	}  

	@SuppressWarnings("rawtypes")
	public List getList(String condicao) throws Exception{  
		Session session = factory.openSession();  
		List usuarios = null;session.createCriteria(condicao);  
		session.flush();  
		session.close();  
		return usuarios;  
	}  

	@SuppressWarnings("unchecked")
	public List<Usuario> getLogin(Usuario usuario) throws Exception{

		List<Usuario> list = null;

		Session session = factory.openSession();  
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM ");
		sql.append("	Usuario u ");
		sql.append(" WHERE ");
		sql.append(" 	u=:usuario");

		Query query = session.createQuery(sql.toString());
		query.setEntity("usuario", usuario);
		list = query.list();

		// List<Usuario> usuarios = session.createQuery("select * from usuario").list();  
		session.flush();  
		session.close();  
		return list;  
	}  

	public Usuario retrieve(Long pk) throws Exception{  
		Session session = factory.openSession();  
		Usuario amigo = (Usuario)session.load(Usuario.class, pk);  
		session.flush();  
		session.close();  
		return amigo;  
	}  

	public void delete(Usuario amigo) throws Exception{  
		Session session = factory.openSession();  
		session.delete(amigo);  
		session.flush();  
		session.close();  
	}  

}
