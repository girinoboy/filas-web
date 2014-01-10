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

import br.com.dto.FrequenciaDTO;
import br.com.factory.HibernateUtility;
import br.com.utility.DataUtils;

/**
 * @author Marcleônio
 *
 */
public class FrequenciaDAO extends GenericoDAO<FrequenciaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	public FrequenciaDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes" })
	public List frequenciaMesPorSexo(String sexo, Integer month, Integer year) throws HibernateException, Exception{

		Calendar dataMax = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);

		Calendar dataMin = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);

		int primeiro_dia_mes = dataMax.getActualMinimum(Calendar.DAY_OF_MONTH);  
		dataMin.set(Calendar.DAY_OF_MONTH, primeiro_dia_mes);

		int ultimo_dia_mes = dataMax.getActualMaximum(Calendar.DAY_OF_MONTH);  
		dataMax.set(Calendar.DAY_OF_MONTH, ultimo_dia_mes);  


		List result = HibernateUtility.getSession().createCriteria(FrequenciaDTO.class)  
				.add(Restrictions.between("dataEntrada", DataUtils.toDateOnly(dataMin.getTime()), DataUtils.toDateOnly(dataMax.getTime())))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("dataEntrada"))
						//.add(Projections.groupProperty("usuarioDTO.sexo"))
						.add(Projections.count("id"))           
						)
				.addOrder(Order.asc("dataEntrada"))
				.createCriteria("usuarioDTO")
				.add(Restrictions.eq("sexo", sexo))
										
				.list();
		
		/*
		String updateQuery = "UPDATE UsuarioDTO obj SET sexo = 1";  
		HibernateUtility.getSession().createQuery(updateQuery)
		//.setString("valor", theme)
		//.setLong("idUsuario",usuario.getId())
		.executeUpdate();
		
		HibernateUtility.commitTransaction();/*/
		return result;
	}

}
