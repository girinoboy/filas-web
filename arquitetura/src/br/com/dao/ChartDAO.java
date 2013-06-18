/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		 query.uniqueResult();
		
		query = session.createSQLQuery("select * from TB_RESPOSTA")
				.addScalar("VAL_PREVISTO", Hibernate.DOUBLE);
		
		Double dadoMinerado1 = (Double) query.uniqueResult();
		
		
		dadoMinerado = dadoMinerado1.intValue();
		/*
			List result = query.list();
			for(int i=0; i<result.size(); i++){
				UsuarioDTO stock = (UsuarioDTO)result.get(i);
				System.out.println(stock.getId());
				dadoMinerado = stock.getId();
			}*/
			/*
			try {  
	            PreparedStatement st = session.connection().prepareStatement(  
	            "{CALL ST_REG_LIN(?,?,?,?)}");  
	            st.setString(1, "permissoes_menus");  
	            st.setString(2,  "perfis_id");  
	            st.setString(3, "menus_id");  
	            st.setDouble(4, previsao);  
	            st.execute();  
	              
	            ResultSet rsRetorno = (ResultSet) st.getResultSet();  
	            
	            
	            if(rsRetorno.next()){
	            	System.out.println(rsRetorno.getString(1));
	            }
	              
	        } catch (SQLException ex) {  
	            ex.printStackTrace();  
	         //   throw new InfrastructureException(ex);  
	        }  */

			return dadoMinerado;
	}

}
