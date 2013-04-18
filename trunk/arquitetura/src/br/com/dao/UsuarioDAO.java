/**
 * 
 */
package br.com.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class UsuarioDAO extends GenericoDAO<UsuarioDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UsuarioDTO verificaLoginSenha(UsuarioDTO usuario) throws HibernateException, Exception {
		try{
			usuario = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
					.add(Restrictions.eq("login", usuario.getLogin()))
					.add(Restrictions.eq("senha", usuario.getSenha()))
					.uniqueResult();
		}catch(Exception e){
			throw e;
		}
		return usuario;

	}

}
