/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PagamentoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author Marcleônio
 *
 */
@SuppressWarnings({ "rawtypes" })
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
	
	
	
	public List mediaLucroAno(Integer year) throws HibernateException, Exception{

		Calendar data = new GregorianCalendar(year, Calendar.MONTH, Calendar.DAY_OF_MONTH);

		Calendar dataMin = new GregorianCalendar(data.get(Calendar.YEAR),Calendar.JANUARY,01);
		Calendar dataMax = new GregorianCalendar(data.get(Calendar.YEAR), Calendar.DECEMBER,31);
		//List a = HibernateUtility.getSession().createCriteria(PagamentoDTO.class).list();
		List result = HibernateUtility.getSession().createCriteria(PagamentoDTO.class)  
				.add(Restrictions.between("mes", dataMin.get(Calendar.MONTH), dataMax.get(Calendar.MONTH)))
				.add(Restrictions.eq("ano", data.get(Calendar.YEAR)))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("mes"))
						.add(Projections.sum("valor"))
					  	)
				.addOrder(Order.asc("mes"))
				.list();
		
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<PagamentoDTO> listWhereIdUsuario(UsuarioDTO usuarioDTO) throws Exception {
		List<PagamentoDTO> result = HibernateUtility.getSession().createCriteria(PagamentoDTO.class)
				.add(Restrictions.eq("usuarioDTO.id", usuarioDTO.getId()))
				.addOrder(Order.asc("dataPagamento"))
				.list();
		
		return result;
	}

}
