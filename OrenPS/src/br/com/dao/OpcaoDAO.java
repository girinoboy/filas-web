/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import br.com.factory.HibernateUtility;
import br.com.models.Opcao;
import br.com.models.Questao;
import br.com.models.Questionario;

/**
 * @author marcleonio.medeiros
 *
 */
public class OpcaoDAO extends GenericoDAO<Opcao, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpcaoDAO() {}
	
	
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
	
	 public void delete2(Opcao objeto) throws Exception {
	        try {
	            HibernateUtility.beginTransaction();
	            HibernateUtility.getSession().delete(objeto);
	            HibernateUtility.commitTransaction();
	            HibernateUtility.closeSession();
	        } catch (HibernateException hibernateException) {
	            cancel();
	            throw hibernateException;
	        }
	    }

}
