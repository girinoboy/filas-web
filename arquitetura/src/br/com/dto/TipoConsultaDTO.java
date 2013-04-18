/**
 * 
 */
package br.com.dto;

/**
 * @author marcleonio.medeiros
 *
 */
public class TipoConsultaDTO {
	
	private Integer id;
	private String nome;
	private String sigla;

	/**
	 * 
	 */
	public TipoConsultaDTO() {
		// TODO Auto-generated constructor stub
	}

	public TipoConsultaDTO(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


}
