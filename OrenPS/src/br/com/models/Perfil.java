package br.com.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcleonio girinoboy@gmail.com
 */
@Entity
@Table(name = "perfis")
public class Perfil {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String observacao;
	@OneToMany(mappedBy = "perfil", targetEntity = PermissaoMenu.class, fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<PermissaoMenu> permissaoMenu;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public List<PermissaoMenu> getPermissaoMenu() {
		return permissaoMenu;
	}
	public void setPermissaoMenu(List<PermissaoMenu> permissaoMenu) {
		this.permissaoMenu = permissaoMenu;
	}


}
