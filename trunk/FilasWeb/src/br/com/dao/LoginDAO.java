package br.com.dao;

import java.util.List;

import org.hibernate.*;

import br.com.config.HibernateUtil;
import br.com.tables.UsuarioDTO;

public class LoginDAO {

	private SessionFactory factory;

	public LoginDAO() throws Exception{  
		//factory = new Configuration().addClass(Usuario.class).buildSessionFactory();
		factory =  (SessionFactory) HibernateUtil.getSessionFactory().getCurrentSession();
	}       

	public void insert(UsuarioDTO amigo) throws Exception{  
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
	public List<UsuarioDTO> getLogin(UsuarioDTO usuario) throws Exception{

		List<UsuarioDTO> list = null;

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

	public UsuarioDTO retrieve(Long pk) throws Exception{  
		Session session = factory.openSession();  
		UsuarioDTO amigo = (UsuarioDTO)session.load(UsuarioDTO.class, pk);  
		session.flush();  
		session.close();  
		return amigo;  
	}  

	public void delete(UsuarioDTO amigo) throws Exception{  
		Session session = factory.openSession();  
		session.delete(amigo);  
		session.flush();  
		session.close();  
	}  

}
