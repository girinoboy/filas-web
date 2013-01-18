/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import br.com.factory.HibernateUtility;
import br.com.models.Questionario;
import br.com.models.Usuario;

/**
 * @author marcleonio.medeiros
 *
 */
public class QuestionarioDAO extends GenericoDAO<Questionario, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionarioDAO() {}
	
	
	public void salvaColunaIndex(Questionario questionario) throws HibernateException, Exception {
		//Nome da classe e atributo
		String updateQuery = "UPDATE Questionario obj SET dashboard_column = :coluna ,item_index =:index WHERE obj.id = :id";  
		HibernateUtility.getSession().createQuery(updateQuery)
		.setInteger("coluna", questionario.getDashboardColumn())
		.setInteger("index", questionario.getItemIndex())
		.setLong("id",questionario.getId())
		.executeUpdate();
		
		HibernateUtility.commitTransaction();
		
	}
	
	
	public List<Questionario> listOrdenada() throws Exception {
        try {
            @SuppressWarnings("unchecked")
			List<Questionario> list = HibernateUtility.getSession().createCriteria(Questionario.class)
            		//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            		.addOrder(Order.asc("dashboardColumn") )
            		.addOrder(Order.asc("itemIndex") )
            		.list();
            //HibernateUtility.closeSession();
            return (List<Questionario>) list;
        } catch (HibernateException hibernateException) {
            cancel();
            throw hibernateException;
        }finally{
        	HibernateUtility.closeSession();
        }
    }


	public Questionario ativaInativaQuestionario(Questionario questionario) throws HibernateException, Exception {
		
		//HibernateUtility.getSession().update("", questionario);  
		
		//Nome da classe e atributo
 		String updateQuery = "UPDATE Questionario obj SET ativo_inativo = :valor WHERE obj.id = :idQuestionario";  
 		HibernateUtility.getSession().createQuery(updateQuery)
 		.setBoolean("valor", questionario.getAtivoInativo())
 		.setLong("idQuestionario",questionario.getId())
 		.executeUpdate();
 		
 		HibernateUtility.commitTransaction();
		
		return questionario;
		
	}

}
