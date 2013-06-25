package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="new_view")
public class NewView {
	
	@Id
	private Double custo;
	private Integer quantidade;
	/**
	 * @return the custo
	 */
	public Double getCusto() {
		return custo;
	}
	/**
	 * @param custo the custo to set
	 */
	public void setCusto(Double custo) {
		this.custo = custo;
	}
	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	

}
