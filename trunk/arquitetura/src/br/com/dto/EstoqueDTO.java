/**
 * 
 */
package br.com.dto;

import javax.persistence.Column;
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
@Table(name="Estoque")
public class EstoqueDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer CodProd;
	@Column(length=30)
	private String Categoria;
	@Column(length=30)
	private String NomePro;
	@Column(length=16)
	private Integer QuantDisp;

}
