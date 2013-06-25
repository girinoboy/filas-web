package br.com.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="new_view")
public class NewView {
	
	@Id
//	private Double custo;
//	private Integer quantidade;
	private Date data;
	private Double venda;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	
	/**
	 * @return the custo
	 */
//	public Double getCusto() {
//		return custo;
//	}
	/**
	 * @param custo the custo to set
	 */
//	public void setCusto(Double custo) {
//		this.custo = custo;
//	}
	/**
	 * @return the quantidade
	 */
//	public Integer getQuantidade() {
//		return quantidade;
//	}
	/**
	 * @param quantidade the quantidade to set
	 */
//	public void setQuantidade(Integer quantidade) {
//		this.quantidade = quantidade;
//	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * @return the venda
	 */
	public Double getVenda() {
		return venda;
	}
	/**
	 * @param venda the venda to set
	 */
	public void setVenda(Double venda) {
		this.venda = venda;
	}
	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}
	/**
	 * @param dia the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/**
	 * @return the ano
	 */
	public Integer getAno() {
		return ano;
	}
	/**
	 * @param ano the ano to set
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	

}
