package br.com.models;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    private Integer id;
    private String login;
    private String senha;
    @Column(name ="ultimo_acesso")
    private Timestamp ultimoAcesso;
    @Column(nullable=false )
    private String tema = "aristo";
    @OneToMany(mappedBy = "usuario", targetEntity = UsuarioPerfil.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UsuarioPerfil> usuarioPeril;
    //private List<Permissao> listaPermissao = new ArrayList<Permissao>();
    private String nome;
    private String email;
    @OneToMany(mappedBy = "usuario", targetEntity = Resposta.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Resposta> resposta;
    @OneToMany(mappedBy = "usuario", targetEntity = UsuarioQuestionario.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UsuarioQuestionario> usuarioQuestionario = new ArrayList<UsuarioQuestionario>();

    public Usuario(Integer id){
    	this.id = id;
    }
    /*
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
    }*//*

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }*/
    
    public Usuario() {}

	public Usuario(String nome, Integer id) {
		this.nome = nome;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		if(tema.equals(null)){
			tema = "aristo";
		}
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

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(List<Resposta> resposta) {
		this.resposta = resposta;
	}

	public List<UsuarioQuestionario> getUsuarioQuestionario() {
		if(usuarioQuestionario==null){
			usuarioQuestionario = new ArrayList<UsuarioQuestionario>();
		}
		return usuarioQuestionario;
	}

	public void setUsuarioQuestionario(List<UsuarioQuestionario> usuarioQuestionario) {
		this.usuarioQuestionario = usuarioQuestionario;
	}

}

