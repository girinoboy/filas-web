package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfilDTO extends AbstractDTO{

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_id", referencedColumnName = "id")
	private UsuarioDTO usuarioDTO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="perfil_id", referencedColumnName = "id")
	private PerfilDTO perfilDTO;
	
	public UsuarioPerfilDTO(){}
	
	public UsuarioPerfilDTO(UsuarioDTO usuarioDTO, PerfilDTO perfilDTO) {
		super();
		this.usuarioDTO = usuarioDTO;
		this.perfilDTO = perfilDTO;
	}



//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}
	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}
}
