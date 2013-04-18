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
@Table(name="especialidade")
public class EspecialidadeDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="especialidade_id")
	private Integer id;
    private String especialidade;

	/**
	 * 
	 */
	public EspecialidadeDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

}
