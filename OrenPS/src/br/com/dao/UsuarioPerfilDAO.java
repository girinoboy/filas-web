/**
 * 
 */
package br.com.dao;

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

/**
 * @author marcleonio.medeiros
 *
 */
public class UsuarioPerfilDAO extends GenericoDAO<UsuarioPerfil, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UsuarioPerfilDAO() {}

	public List<UsuarioPerfil> listPorUsuario(Integer id) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<UsuarioPerfil> list = HibernateUtility.getSession().createCriteria(UsuarioPerfil.class)
			.add(Restrictions.eq("usuario.id", id))
			.list();
			//HibernateUtility.closeSession();
			return (List<UsuarioPerfil>) list;
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

	public List<UsuarioPerfil> listPerfisRestantes(Usuario usuario) throws Exception {
		try {
			 List<UsuarioPerfil> list2 = listPorUsuario(usuario.getId());
			 
			 List<Integer> ids = new ArrayList<Integer>();  
			 for (UsuarioPerfil usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfil().getId());
			}
			 
			@SuppressWarnings("unchecked")
			List<UsuarioPerfil> list = HibernateUtility.getSession().createCriteria(UsuarioPerfil.class)
			.add(Restrictions.not(Restrictions.in("id", ids )))
			.add(Restrictions.eq("usuario.id", usuario.getId()))
			.list();
			//HibernateUtility.closeSession();
			return (List<UsuarioPerfil>) list;
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

	public List<UsuarioPerfil> listPerfisUsuario(Usuario usuario) throws Exception {
		try {
			return listPorUsuario(usuario.getId());
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
