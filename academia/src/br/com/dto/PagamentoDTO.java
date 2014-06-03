/**
 * 
 */
package br.com.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "pagamento")
public class PagamentoDTO extends AbstractDTO{

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Integer id;
	private Double valor;
	@Column(name ="data_pagamento")
	private Date dataPagamento;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	private Integer vezesSemana;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;


	/**
	 * 
	 */
	public PagamentoDTO() {
		getDataPagamento();
		if(dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);

			dia = data.get(Calendar.DAY_OF_MONTH);

			mes = data.get(Calendar.MONTH);

			ano = data.get(Calendar.YEAR);
			
			setDia(dia);
			setMes(mes);
			setAno(ano);

		}
		getDia();
		getMes();
		getAno();


	}


//	public Integer getId() {
//		return id;
//	}
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}


	public Date getDataPagamento() {
		getDia();
		getMes();
		getAno();
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}


	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Integer getDia() {
		if(dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			dia = data.get(Calendar.DAY_OF_MONTH);

		}
		return dia;
	}


	public void setDia(Integer dia) {
		this.dia = dia;
	}


	public Integer getMes() {
		if(dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			mes = data.get(Calendar.MONTH);
		}
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public Integer getAno() {
		if(dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			ano = data.get(Calendar.YEAR);
		}
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Integer getVezesSemana() {
		return vezesSemana;
	}


	public void setVezesSemana(Integer vezesSemana) {
		this.vezesSemana = vezesSemana;
	}

}
