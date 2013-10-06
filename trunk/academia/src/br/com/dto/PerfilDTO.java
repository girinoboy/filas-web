package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marcleonio girinoboy@gmail.com
 */
@Entity
@Table(name = "perfil")
public class PerfilDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private String observacao;
	//@OneToMany(mappedBy = "perfil", targetEntity = PermissaoMenu.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	//@OrderBy(value ="Perfil.descricao") 
	//private List<PermissaoMenu> permissaoMenu;
	
	/**
	 * 
	 */
	public PerfilDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerfilDTO other = (PerfilDTO) obj;
        if (this.id == null){
        	return false;
        }
        if (!this.id.equals(other.id)) {
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
//	public List<PermissaoMenu> getPermissaoMenu() {
//		return permissaoMenu;
//	}
//	public void setPermissaoMenu(List<PermissaoMenu> permissaoMenu) {
//		this.permissaoMenu = permissaoMenu;
//	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


}
