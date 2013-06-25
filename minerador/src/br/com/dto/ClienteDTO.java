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
@Table(name="Cliente")
public class ClienteDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	private Integer CodClien;
	@Column(length=40)
	private String Nome;
	@Column(length=11)
	private String Telefone;
	@Column(length=30)
	private String Endereco;
	@Column(length=40)
	private String Email;
	@Column(length=12)
	private String CPF;
}
