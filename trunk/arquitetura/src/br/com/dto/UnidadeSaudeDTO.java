/**
 * 
 */
package br.com.dto;

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
@Table(name="unidade_saude")
public class UnidadeSaudeDTO {
	
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="unidade_saude_id")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instituicao_id", insertable = true, updatable = true, nullable = true)
	private InstituicaoDTO instituicao;
	@Column(name="nome_unidade_saude")
	private String nomeUnidadeSaude;

	/**
	 * 
	 */
	public UnidadeSaudeDTO() {
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
	 * @return the instituicao
	 */
	public InstituicaoDTO getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao the instituicao to set
	 */
	public void setInstituicao(InstituicaoDTO instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return the nomeUnidadeSaude
	 */
	public String getNomeUnidadeSaude() {
		return nomeUnidadeSaude;
	}

	/**
	 * @param nomeUnidadeSaude the nomeUnidadeSaude to set
	 */
	public void setNomeUnidadeSaude(String nomeUnidadeSaude) {
		this.nomeUnidadeSaude = nomeUnidadeSaude;
	}

}
