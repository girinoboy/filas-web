/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.dto.ConsultaDTO;
import br.com.dto.HorarioAtendimentoSubUnidadeDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class HorarioAtendimentoSubUnidadeDAO extends GenericoDAO<HorarioAtendimentoSubUnidadeDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HorarioAtendimentoSubUnidadeDAO() {
		// TODO Auto-generated constructor stub
	}





	public HorarioAtendimentoSubUnidadeDTO consultarEspecialidadesUnidadeSaude(HorarioAtendimentoSubUnidadeDTO hasu) throws HibernateException, Exception {
		try{
			hasu = (HorarioAtendimentoSubUnidadeDTO) HibernateUtility.getSession().createCriteria(HorarioAtendimentoSubUnidadeDTO.class)
					.add(Restrictions.eq("unidade_saude_id", hasu.getUnidadeSaudeDTO().getId()))
					.add(Restrictions.eq("data_termino", hasu.getDataTermino()))
					.uniqueResult();
		}catch(Exception e){
			throw e;
		}
		return hasu;

	}

	public List consultarProfissionaisEspecialidadeMarcarConsulta(ConsultaDTO consulta) throws HibernateException, Exception {
		try{
			List  profissionais = HibernateUtility.getSession().createCriteria(HorarioAtendimentoSubUnidadeDTO.class)
					.add(Restrictions.eq("especialidade_id", consulta.getEspecialidadeDTO().getId()))
					.add(Restrictions.eq("unidade_saude_id", consulta.getUnidadeSaudeDTO().getId()))
					.add(Restrictions.eq("data_inicio", consulta.getDataHoraInic()))
					.add(Restrictions.eq("data_termino", consulta.getDataHoraInic()))
					.add(Restrictions.eq("dia_semana", consulta.getDataHoraInic()))
					.add(Restrictions.eq("data_termino", consulta.getDataHoraInic().getDay()))
					.addOrder( Order.asc("usuario.nome")) 
					.setResultTransformer(new AliasToBeanResultTransformer(UsuarioDTO.class))
					.list();

			return profissionais;

		}catch(Exception e){
			throw e;
		}

	}





	public List<HorarioAtendimentoSubUnidadeDTO> consultarHorariosDisponiveis(
			ConsultaDTO consulta) throws Exception {
		try{
			@SuppressWarnings("unchecked")
			List<HorarioAtendimentoSubUnidadeDTO> list = HibernateUtility.getSession().createCriteria(HorarioAtendimentoSubUnidadeDTO.class)
					.add(Restrictions.eq("especialidade_id", consulta.getEspecialidadeDTO().getId()))
					.add(Restrictions.eq("unidade_saude_id", consulta.getUnidadeSaudeDTO().getId()))
					.add(Restrictions.eq("data_inicio", consulta.getDataHoraInic()))
					.add(Restrictions.eq("data_termino", consulta.getDataHoraInic()))
					.add(Restrictions.eq("dia_semana", consulta.getDataHoraInic()))
					.add(Restrictions.eq("data_termino", consulta.getDataHoraInic()))
					.add(Restrictions.eq("usuario.id", consulta.getProfissionaDTO().getId()))
					//.addOrder( Order.asc("usuario.nome")) 
					.list();

			return list;
		}catch(Exception e){
			throw e;
		}
	}

}
