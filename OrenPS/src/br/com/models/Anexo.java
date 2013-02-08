/**
 * 
 */
package br.com.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "anexos")
public class Anexo {
	
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(columnDefinition="BLOB")
	private byte[] anexo;
	private String nome;
	@Column(name ="content_type")
	private String contentType;
	private Long tamanho;

	/**
	 * 
	 */
	public Anexo() {
		// TODO Auto-generated constructor stub
	}
	
	public Anexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

}
