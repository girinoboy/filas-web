/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.Questao;
import br.com.models.Questionario;

/**
 * @author marcleonio.medeiros
 *
 */
public class QuestaoDAO extends GenericoDAO<Questao, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestaoDAO() {}


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


	public List<Questao> listarPorQuestionario(Long id) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<Questao> list = HibernateUtility.getSession().createCriteria(Questao.class)
			//.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.addOrder(Order.asc("id") )
			.add(Restrictions.eq("questionario.id", id))
			.list();
			//HibernateUtility.closeSession();
			return (List<Questao>) list;
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
