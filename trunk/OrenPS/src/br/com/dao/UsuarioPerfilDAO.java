/**
 * 
 */
package br.com.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.Questao;
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

	public List<UsuarioPerfil> listPorUsuario(Long id) throws Exception {
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

}
