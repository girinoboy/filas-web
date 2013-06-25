/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import br.com.dto.NewView;

/**
 * @author Marcleônio
 *
 */
public class ChartDAO extends GenericoDAO<NewView, Serializable>{
	
	
	public Double minerar(String tabela,String colunaX, String colunaY,Double previsao){
		Integer dadoMineradoI = 0;
		Query query = session.createSQLQuery(
				"CALL ST_REG_LIN(:tabela,:colunax,:colunay,:previsao)")
				//.addScalar("VAL_PREVISTO", Hibernate.DOUBLE)
				//.addEntity(UsuarioDTO.class)
				.setParameter("tabela", tabela)
				.setParameter("colunax", colunaX)
				.setParameter("colunay", colunaY)
				.setParameter("previsao", previsao);
		 query.uniqueResult();
		
		query = session.createSQLQuery("select * from TB_RESPOSTA")
				.addScalar("VAL_PREVISTO", Hibernate.DOUBLE);
		
		Double dadoMinerado = (Double) query.uniqueResult();
		
		
		//dadoMinerado = dadoMinerado1.intValue();
		/*
			List result = query.list();
			for(int i=0; i<res ult.size(); i++){
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
