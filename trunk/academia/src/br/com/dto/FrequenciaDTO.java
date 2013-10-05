/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
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
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "frequencia")
public class FrequenciaDTO {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(name ="data_entrada")
	private Date dataEntrada;
	@Column(name ="data_completa")
	private Date dataCompleta;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;

	/**
	 * 
	 */
	public FrequenciaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public Date getDataCompleta() {
		return dataCompleta;
	}

	public void setDataCompleta(Date dataCompleta) {
		this.dataCompleta = dataCompleta;
	}
	

}
