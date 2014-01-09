/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

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


	public UsuarioDTO verificaLoginSenha(UsuarioDTO usuarioDTO) throws HibernateException, Exception {
		try{
			usuarioDTO = (UsuarioDTO) HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
					.add(Restrictions.eq("usuario", usuarioDTO.getUsuario()))
					.add(Restrictions.eq("senha", usuarioDTO.getSenha()))
					.uniqueResult();
		}catch(Exception e){
			throw e;
		}
		return usuarioDTO;

	}

	public void saveTheme(String theme, UsuarioDTO usuario) throws HibernateException, Exception {
		//Nome da classe e atributo
		String updateQuery = "UPDATE UsuarioDTO obj SET tema = :valor WHERE obj.id = :id";  
		HibernateUtility.getSession().createQuery(updateQuery)
		.setString("valor", theme)
		.setLong("id",usuario.getId())
		.executeUpdate();

		HibernateUtility.commitTransaction();

	}


	public void create(List<UsuarioDTO> listUser) throws HibernateException, Exception {

		for (UsuarioDTO usuario : listUser) { 
			HibernateUtility.getSession().save(usuario);
		}
	}


	@SuppressWarnings("rawtypes")
	public List frequenciaMes() throws HibernateException, Exception {

		//Criteria crit = HibernateUtility.getSession().createCriteria(UsuarioDTO.class) ;
		/*Criteria crit = HibernateUtility.getSession().createCriteria(UsuarioDTO.class);
		crit.createCriteria(UsuarioDTO.class).add;

        cri.add(Restrictions.eq("codcliente",codcliente));  
        cri.add(Restrictions.between("dataemissao", new java.sql.Date(calendar.getTimeInMillis()),   new java.sql.Date(calendar2.getTimeInMillis())));  

            return cri.list();  

		 return HibernateUtility.getSession().createQuery("from UsuarioDTO where month(data) = :mes and year(data) = :ano")
				 .setString("valor", theme)
		 .list();*/
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<UsuarioDTO> filtrar(UsuarioDTO usuarioDTO) throws HibernateException, Exception {

		try {
			List<UsuarioDTO> list = HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
					//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.ilike("nome",usuarioDTO.getNome() ==null ? "":"%"+usuarioDTO.getNome()+"%"))
					.list();
			//HibernateUtility.closeSession();
			return (List<UsuarioDTO>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			//HibernateUtility.closeSession();
		}
	}

}
