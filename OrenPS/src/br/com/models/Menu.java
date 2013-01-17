package br.com.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 *
 * @author Marcleonio
 */
@Entity
@Table(name = "menus")
public class Menu {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menus_id", insertable = true, updatable = false, nullable = true)
	private Menu sub;
	private String descricao;
	private String url;
	private String pagina;
	private String icone;
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@JoinColumn(name="questionarios_id", referencedColumnName = "id", insertable = true, updatable = false, nullable = true)
	private Questionario questionario;
	

	//colocar isso em uma interface depois para diferenciar os menus de questionarios
	//private String url;
	@Column(name="icone_nativo")
	private String iconeNativo;
/*
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Menu other = (Menu) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}*/
/*
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + this.id;
		return hash;
	}*/
	public Menu() {}

	public Menu(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getSub() {
		return sub;
	}

	public void setSub(Menu sub) {
		this.sub = sub;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getIconeNativo() {
		return iconeNativo;
	}

	public void setIconeNativo(String iconeNativo) {
		this.iconeNativo = iconeNativo;
	}

	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		if(questionario == null){
			questionario = new Questionario();
		}
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
}