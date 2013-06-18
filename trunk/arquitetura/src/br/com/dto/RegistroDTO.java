/**
 * 
 */
package br.com.dto;

import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Embeddable
@Table(name="Registro")
public class RegistroDTO {
	
	private Integer Fileira;
	private Estoque CodProd;
	
	Coluna Char(4),
	
	private String Prateleira Varchar(4),

}
