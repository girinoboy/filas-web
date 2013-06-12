/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.dto.EspecialidadeDTO;
import br.com.dto.HorarioAtendimentoSubUnidadeDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class EspecialidadeDAO extends GenericoDAO<EspecialidadeDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EspecialidadeDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<EspecialidadeDTO> consultarEspecialidadesUnidadeSaude(HorarioAtendimentoSubUnidadeDTO hasu) throws HibernateException, Exception{
		
		@SuppressWarnings("unchecked")
		List<EspecialidadeDTO> result = HibernateUtility.getSession().createCriteria(HorarioAtendimentoSubUnidadeDTO.class)  
				.add(Restrictions.eq("unidadeSaudeDTO.id", hasu.getUnidadeSaudeDTO().getId()))
				.add(Restrictions.gt("dataTermino", hasu.getDataTermino()))
				.add(Restrictions.isNotNull(("especialidadeDTO.especialidade")))
				.createAlias("especialidadeDTO", "especialidadeDTO")
				//.setProjection( Property.forName("especialidadeDTO.id").group() )
                .setProjection(Projections.projectionList()
                		.add( Projections.property("especialidadeDTO.id").as("id") )//diz aonde o hibernate joga a propriedade
        			    .add( Projections.property("especialidadeDTO.especialidade").as("especialidade") )//diz aonde o hibernate joga o valor do objeto
                        .add(Projections.groupProperty("especialidadeDTO.id"))
                        .add(Projections.groupProperty("especialidadeDTO.especialidade"))
                )
                //.addEntity("especialidadeDTO", EspecialidadeDTO.class)
                //.setResultTransformer(Transformers.aliasToBean(EspecialidadeDTO.class))
                .setResultTransformer(new AliasToBeanResultTransformer(EspecialidadeDTO.class))
                .addOrder( Order.asc("especialidadeDTO.especialidade")) 
                .list();
		
		
		return  result;
	}

}
