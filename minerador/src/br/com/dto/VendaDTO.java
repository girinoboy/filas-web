package br.com.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Embeddable
@Table(name="Venda")
public class VendaDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private Integer NotaFisc;
	private Integer CodClien;
	private Integer CodServ;
	private Double Preco;// Decimal(10,2),

}
