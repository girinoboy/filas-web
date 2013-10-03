/**
 * 
 */
package br.com.dto;

import java.io.ByteArrayInputStream;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "anexos")
public class AnexoDTO {


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
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;

	/**
	 * 
	 */
	public AnexoDTO() {
		// TODO Auto-generated constructor stub
	}

	public AnexoDTO(byte[] anexo) {
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
	public StreamedContent getImage(){
		if(anexo !=null)
			return new DefaultStreamedContent(new ByteArrayInputStream(anexo),"image/png");
		else
			return new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]),"image/png");
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
