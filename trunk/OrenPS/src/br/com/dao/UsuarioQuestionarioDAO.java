/**
 * 
 */
package br.com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.Usuario;
import br.com.models.UsuarioQuestionario;

/**
 * @author Marcleônio
 *
 */
public class UsuarioQuestionarioDAO extends GenericoDAO<UsuarioQuestionario, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<UsuarioQuestionario> a(){
		List<UsuarioQuestionario> list = null;
		try{
			Criteria criteriaTable1 = HibernateUtility.getSession().createCriteria(UsuarioQuestionario.class);
			//Criteria criteriaTable2 = criteriaTable1.createCriteria("usuarioQuestionario", CriteriaSpecification.LEFT_JOIN);
			//Criteria criteriaTable3 = criteriaTable1.createCriteria("questionario",CriteriaSpecification.LEFT_JOIN);
			
			criteriaTable1.setFetchMode("usuario", FetchMode.JOIN); 
			
			list = criteriaTable1.list();
			
			//criteriaTable2.list();
			
			 
			/*
			Criteria criteria = HibernateUtility.getSession().createCriteria(UsuarioQuestionario.class);
			criteria.add(Restrictions.or(Restrictions.isNull("breed.pkBreed")).add(Restrictions.ne(breed.desc,"Egyptian cat")));
			List<Owners> list= criteria.createCriteria("owner",JoinType.RIGHT_OUTER_JOIN);
			
			Criteria criteria7 = HibernateUtility.getSession().createCriteria(UsuarioQuestionario.class);
			List<UsuarioQuestionario> list9= criteria7.createCriteria("owner",JoinType.RIGHT_OUTER_JOIN);
			*/
			/*

	    DetachedCriteria criteria = DetachedCriteria.forClass(UsuarioQuestionario.class,"Remessa");  
	    criteria.createAlias("Remessa.repasses", "Repasses",CriteriaSpecification.LEFT_JOIN);  
	    criteria.add(Restrictions.eq("Remessa.id", idRemessa));  
	    criteria.getExecutableCriteria(hibernateSession).uniqueResult();  


	    Usuario usuario;
	    return HibernateUtility.getSession().createCriteria(UsuarioQuestionario.class)
	    		.add(Restrictions.eq("produtoEmpLazy", usuario))  
	            .list();  */
			System.out.println(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}
}
