/**
 * 
 */
package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.UsuarioDTO;
import br.com.dto.UsuarioPerfilDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class UsuarioPerfilDAO extends GenericoDAO<UsuarioPerfilDTO, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UsuarioPerfilDAO() {}

	public List<UsuarioPerfilDTO> listPorUsuario(Integer id) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<UsuarioPerfilDTO> list = HibernateUtility.getSession().createCriteria(UsuarioPerfilDTO.class)
			.add(Restrictions.eq("usuario.id", id))
			.list();
			//HibernateUtility.closeSession();
			return (List<UsuarioPerfilDTO>) list;
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

	public List<UsuarioPerfilDTO> listPerfisRestantes(UsuarioDTO usuario) throws Exception {
		try {
			 List<UsuarioPerfilDTO> list2 = listPorUsuario(usuario.getId());
			 
			 List<Integer> ids = new ArrayList<Integer>();  
			 for (UsuarioPerfilDTO usuarioPerfil : list2) {
				ids.add(usuarioPerfil.getPerfilDTO().getId());
			}
			 
			@SuppressWarnings("unchecked")
			List<UsuarioPerfilDTO> list = HibernateUtility.getSession().createCriteria(UsuarioPerfilDTO.class)
			.add(Restrictions.not(Restrictions.in("id", ids )))
			.add(Restrictions.eq("usuario.id", usuario.getId()))
			.list();
			//HibernateUtility.closeSession();
			return (List<UsuarioPerfilDTO>) list;
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

	public List<UsuarioPerfilDTO> listPerfisUsuario(UsuarioDTO usuario) throws Exception {
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
