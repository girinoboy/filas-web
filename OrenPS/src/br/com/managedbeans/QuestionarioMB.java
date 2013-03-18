/**
 * 
 */
package br.com.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.models.Questionario;
import br.com.utility.QuestionarioConverter;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class QuestionarioMB {
	
	private List<Questionario> listQuestionario;

	/**
	 * 
	 */
	public QuestionarioMB() {
		listQuestionario = QuestionarioConverter.listQuestionario; 
	}

	/**
	 * @return the listQuestionario
	 */
	public List<Questionario> getListQuestionario() {
		return listQuestionario;
	}

	/**
	 * @param listQuestionario the listQuestionario to set
	 */
	public void setListQuestionario(List<Questionario> listQuestionario) {
		this.listQuestionario = listQuestionario;
	}

}
