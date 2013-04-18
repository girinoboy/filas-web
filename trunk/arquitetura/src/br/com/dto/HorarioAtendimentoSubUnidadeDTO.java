/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="horario_atendimento_subunidade")
public class HorarioAtendimentoSubUnidadeDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="horario_atd_sub_id")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_saude_id", insertable = true, updatable = true, nullable = true)
	private UnidadeSaudeDTO unidadeSaudeDTO;
	@Column(name="data_inicio")
	private Date dataInicio;
	@Column(name="data_termino")
	private Date dataTermino;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "especialidade_id", insertable = true, updatable = true, nullable = true)
	private EspecialidadeDTO especialidadeDTO;
	@Column(name="dia_semana")
	private Integer diaSemana;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;
	
	

	/**
	 * 
	 */
	public HorarioAtendimentoSubUnidadeDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the unidadeSaudeDTO
	 */
	public UnidadeSaudeDTO getUnidadeSaudeDTO() {
		if(unidadeSaudeDTO == null){
			unidadeSaudeDTO = new UnidadeSaudeDTO();
		}
		return unidadeSaudeDTO;
	}

	/**
	 * @param unidadeSaudeDTO the unidadeSaudeDTO to set
	 */
	public void setUnidadeSaudeDTO(UnidadeSaudeDTO unidadeSaudeDTO) {
		this.unidadeSaudeDTO = unidadeSaudeDTO;
	}

	/**
	 * @return the dataTermino
	 */
	public Date getDataTermino() {
		return dataTermino;
	}

	/**
	 * @param dataTermino the dataTermino to set
	 */
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the especialidadeDTO
	 */
	public EspecialidadeDTO getEspecialidadeDTO() {
		return especialidadeDTO;
	}

	/**
	 * @param especialidadeDTO the especialidadeDTO to set
	 */
	public void setEspecialidadeDTO(EspecialidadeDTO especialidadeDTO) {
		this.especialidadeDTO = especialidadeDTO;
	}

	/**
	 * @return the diaSemana
	 */
	public Integer getDiaSemana() {
		return diaSemana;
	}

	/**
	 * @param diaSemana the diaSemana to set
	 */
	public void setDiaSemana(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}

	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
