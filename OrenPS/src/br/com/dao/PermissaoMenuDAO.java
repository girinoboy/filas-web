/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.PermissaoMenu;

/**
 * @author marcleonio.medeiros
 *
 */
public class PermissaoMenuDAO extends GenericoDAO<PermissaoMenu, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<PermissaoMenu> listByIdPerfil(Integer id) throws Exception {
		try {
			 
			@SuppressWarnings("unchecked")
			List<PermissaoMenu> list = HibernateUtility.getSession().createCriteria(PermissaoMenu.class)
			.add(Restrictions.not(Restrictions.eq("id", id )))
			.list();
			//HibernateUtility.closeSession();
			return (List<PermissaoMenu>) list;
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
