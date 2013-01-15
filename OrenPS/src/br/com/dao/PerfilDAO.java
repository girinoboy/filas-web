package br.com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.Perfil;
import br.com.models.Questao;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;

public class PerfilDAO extends GenericoDAO<Perfil, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioPerfilDAO usuarioPerfilDAO;

	public List<Perfil> listPerfisRestantes(Usuario usuario) throws Exception {
		usuarioPerfilDAO = new UsuarioPerfilDAO();
		try {
			 List<UsuarioPerfil> list2 = usuarioPerfilDAO.listPorUsuario(usuario.getId());
			 
			 List<Integer> ids = new ArrayList<Integer>();  
			 for (UsuarioPerfil usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfil().getId());
			}
			 
			@SuppressWarnings("unchecked")
			List<Perfil> list = HibernateUtility.getSession().createCriteria(Perfil.class)
			.add(Restrictions.not(Restrictions.in("id", ids )))
			.list();
			//HibernateUtility.closeSession();
			return (List<Perfil>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			HibernateUtility.closeSession();
		}
		
		
	}

	public List<Perfil> listPerfisUsuario(Usuario usuario) throws Exception {
		usuarioPerfilDAO = new UsuarioPerfilDAO();
		try {
			 List<UsuarioPerfil> list2 = usuarioPerfilDAO.listPorUsuario(usuario.getId());
			 
			 List<Integer> ids = new ArrayList<Integer>();  
			 for (UsuarioPerfil usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfil().getId());
			}
			 
			@SuppressWarnings("unchecked")
			List<Perfil> list = HibernateUtility.getSession().createCriteria(Perfil.class)
			.add(Restrictions.in("id", ids ))
			.list();
			//HibernateUtility.closeSession();
			return (List<Perfil>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			HibernateUtility.closeSession();
		}
	}

}
