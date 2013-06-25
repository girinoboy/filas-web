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
@Table(name="Fornecedor")
public class FornecedorDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer CodForn;
	@Column(length=40)
	private String NomeFor;
	@Column(length=15)
	private String CNPJ;
}
