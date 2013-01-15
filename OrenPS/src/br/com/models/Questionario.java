package br.com.models;

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

/**
*
* @author Marcleonio
*/
@Entity
@Table(name = "questionarios")
public class Questionario {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	@OneToMany(mappedBy = "questionario", targetEntity = Questao.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Questao> questao;
	@Column(name="dashboard_column")
	private Integer dashboardColumn = 0;//default 0
	@Column(name="item_index")
	private Integer itemIndex = 0;

	public Questionario() {}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the questao
	 */
	public List<Questao> getQuestao() {
		return questao;
	}

	/**
	 * @param questao the questao to set
	 */
	public void setQuestao(List<Questao> questao) {
		this.questao = questao;
	}

	/**
	 * @return the dashboardColumn
	 */
	public Integer getDashboardColumn() {
		return (dashboardColumn == null) ? 0 : dashboardColumn;
	}

	/**
	 * @param dashboardColumn the dashboardColumn to set
	 */
	public void setDashboardColumn(Integer dashboardColumn) {
		this.dashboardColumn = dashboardColumn;
	}

	/**
	 * @return the itemIndex
	 */
	public Integer getItemIndex() {
		if(itemIndex ==null){
			itemIndex = 0;
		}
		return itemIndex;
	}

	/**
	 * @param itemIndex the itemIndex to set
	 */
	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}
}
