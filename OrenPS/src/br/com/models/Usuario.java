package br.com.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String login;
    private String senha;
    @Column(name ="ultimo_acesso")
    private Timestamp ultimoAcesso;
    private String tema;
    @OneToMany(mappedBy = "usuario", targetEntity = UsuarioPerfil.class, fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UsuarioPerfil> usuarioPeril;
    //private List<Permissao> listaPermissao = new ArrayList<Permissao>();

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Timestamp getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Timestamp ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<UsuarioPerfil> getUsuarioPeril() {
		return usuarioPeril;
	}

	public void setUsuarioPeril(List<UsuarioPerfil> usuarioPeril) {
		this.usuarioPeril = usuarioPeril;
	}
}

