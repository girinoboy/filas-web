package br.com.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios_perfis")
public class UsuarioPerfil {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuarios_id", referencedColumnName = "id")
    private Usuario usuario = new Usuario();
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="perfis_id", referencedColumnName = "id")
    private Perfil perfil = new Perfil();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
