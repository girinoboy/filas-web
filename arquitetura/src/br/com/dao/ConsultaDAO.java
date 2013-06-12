/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultScheduleEvent;

import br.com.dto.ConsultaDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ConsultaDAO extends GenericoDAO<ConsultaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ConsultaDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<DefaultScheduleEvent> findEventsBetween(Date start, Date end) throws HibernateException, Exception {
		List list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(ConsultaDTO.class).
					add(Restrictions.between("dataHoraInic",start,end)).list();
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	
	public List<ConsultaDTO> consultarEntreDatas(Date start, Date end) {
		List<ConsultaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(ConsultaDTO.class).
					add(Restrictions.between("dataHoraInic",start,end)).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
