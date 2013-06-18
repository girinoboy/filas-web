/**
 * 
 */
package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="Cargo")
public class CargoDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private Integer CodCargo;
	private Integer CodUni;
	private String DescCargo Varchar(30);
	private Double Salario Decimal(10,2);
	private Double Produtividade Decimal(10,2);
	private String PlanoCarreira Varchar(30);
	private Integer CargaHoraria;

}
