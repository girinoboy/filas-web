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
@Table(name="consulta")
public class ConsultaDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_consulta")
	private Integer id;
	@Column(name="data_hora_inic")
	private Date dataHoraInic;
	@Column(name="data_hora_term")
	private Date dataHoraTerm;
	@Column(length=1, columnDefinition = "char(1) default 'N'")
	private String encaixe;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instituicao_id", insertable = true, updatable = true, nullable = true)
	private InstituicaoDTO instituicaoDTO;//TODO ja existe instituição na unidade de saude, portanto não precisa salvar esse item. colocar como nulo na proxima
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO pacienteDTO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profissional_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO profissionaDTO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "especialidade_id", insertable = true, updatable = true, nullable = true)
	private EspecialidadeDTO especialidadeDTO;
	@Column(name="situacao_consulta",length=1, columnDefinition = "char(1) default 'N'")
	private String situacaoConsulta;
	private String obs;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_saude_id", insertable = true, updatable = true, nullable = true)
	private UnidadeSaudeDTO unidadeSaudeDTO;
	@Column(name="tipo_consulta",length=1, columnDefinition = "char(1) default 'N'")
	private String tipoConsulta;
	
	/**
	 * 
	 */
	public ConsultaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraInic() {
		return dataHoraInic;
	}

	public void setDataHoraInic(Date dataHoraInic) {
		this.dataHoraInic = dataHoraInic;
	}

	public Date getDataHoraTerm() {
		return dataHoraTerm;
	}

	public void setDataHoraTerm(Date dataHoraTerm) {
		this.dataHoraTerm = dataHoraTerm;
	}

	public String getEncaixe() {
		return encaixe;
	}

	public void setEncaixe(String encaixe) {
		this.encaixe = encaixe;
	}

	public InstituicaoDTO getInstituicaoDTO() {
		return instituicaoDTO;
	}

	public void setInstituicaoDTO(InstituicaoDTO instituicaoDTO) {
		this.instituicaoDTO = instituicaoDTO;
	}

	public UsuarioDTO getPacienteDTO() {
		return pacienteDTO;
	}

	public void setPacienteDTO(UsuarioDTO pacienteDTO) {
		this.pacienteDTO = pacienteDTO;
	}

	public UsuarioDTO getProfissionaDTO() {
		if(profissionaDTO==null){
			profissionaDTO = new UsuarioDTO();
		}
		return profissionaDTO;
	}

	public void setProfissionaDTO(UsuarioDTO profissionaDTO) {
		this.profissionaDTO = profissionaDTO;
	}

	public EspecialidadeDTO getEspecialidadeDTO() {
		if(especialidadeDTO == null){
			especialidadeDTO = new EspecialidadeDTO();
		}
		return especialidadeDTO;
	}

	public void setEspecialidadeDTO(EspecialidadeDTO especialidadeDTO) {
		this.especialidadeDTO = especialidadeDTO;
	}

	public String getSituacaoConsulta() {
		return situacaoConsulta;
	}

	public void setSituacaoConsulta(String situacaoConsulta) {
		this.situacaoConsulta = situacaoConsulta;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public UnidadeSaudeDTO getUnidadeSaudeDTO() {
		if(unidadeSaudeDTO==null){
			unidadeSaudeDTO = new UnidadeSaudeDTO();
		}
		return unidadeSaudeDTO;
	}

	public void setUnidadeSaudeDTO(UnidadeSaudeDTO unidadeSaudeDTO) {
		this.unidadeSaudeDTO = unidadeSaudeDTO;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

}
