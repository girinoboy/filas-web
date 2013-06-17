/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;

import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
public class ChartDAO extends GenericoDAO<UsuarioDTO, Serializable>{
	
	
	public Integer minerar(Double previsao){
		Integer dadoMinerado = 0;
		Query query = session.createSQLQuery(
				"CALL ST_REG_LIN(:tabela,:colunax,:colunay,:previsao)")
				//.addScalar("VAL_PREVISTO", Hibernate.DOUBLE)
				//.addEntity(UsuarioDTO.class)
				.setParameter("tabela", "permissoes_menus")
				.setParameter("colunax", "perfis_id")
				.setParameter("colunay", "menus_id")
				.setParameter("previsao", previsao);
		dadoMinerado = (Integer) query.uniqueResult();
			List result = query.list();/*
			for(int i=0; i<result.size(); i++){
				UsuarioDTO stock = (UsuarioDTO)result.get(i);
				System.out.println(stock.getId());
				dadoMinerado = stock.getId();
			}*/

			return dadoMinerado;
	}

}
