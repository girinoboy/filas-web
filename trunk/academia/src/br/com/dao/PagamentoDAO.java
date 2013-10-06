/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PagamentoDTO;
import br.com.factory.HibernateUtility;

/**
 * @author Marcleônio
 *
 */
public class PagamentoDAO extends GenericoDAO<PagamentoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PagamentoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	public List mediaLucroAno(String mes) throws HibernateException, Exception{

		Calendar data = new GregorianCalendar();

		Calendar dataMin = new GregorianCalendar(data.get(Calendar.YEAR),Calendar.JANUARY,01);
		Calendar dataMax = new GregorianCalendar(data.get(Calendar.YEAR), Calendar.DECEMBER,31);

		List result = HibernateUtility.getSession().createCriteria(PagamentoDTO.class)  
				.add(Restrictions.between("mes", dataMin.get(Calendar.MONTH), dataMax.get(Calendar.MONTH)))
				.add(Restrictions.eq("ano", data.get(Calendar.YEAR)))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("mes"))
						.add(Projections.avg("valor"))
					  	)
				.addOrder(Order.asc("mes"))
				.list();
		
		return result;
	}

}
