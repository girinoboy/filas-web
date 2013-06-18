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
@Table(name="Especializacao")
public class EspecializacaoDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer CodUni;
	@Column(length=80)
	private String DescEspec;
	@Column(length=50)
	private String Certif;

}
