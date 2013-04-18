/**
 * 
 */
package br.com.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="instituicao_saude")
public class InstituicaoDTO {

	
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="instituicao_id")
	private Integer id;
	@Column(name="nome_instituicao")
	private String nomeInstituicao;
	
	/**
	 * 
	 */
	public InstituicaoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

}
