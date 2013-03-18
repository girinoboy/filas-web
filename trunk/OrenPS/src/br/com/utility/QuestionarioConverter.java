/**
 * 
 */
package br.com.utility;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.QuestionarioDAO;
import br.com.models.Questionario;

/**
 * @author Marcleônio
 *
 */
@FacesConverter(value="questionario")
public class QuestionarioConverter implements Converter {  
	
	public static List<Questionario> listQuestionario;
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();

	/**
	 * 
	 */
	public QuestionarioConverter() {
		try {
			listQuestionario = questionarioDAO.listOrdenada();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue)
			throws ConverterException {
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int number = Integer.parseInt(submittedValue);  
	  
	                for (Questionario q : listQuestionario) {  
	                    if (q.getId() == number) {  
	                        return q;  
	                    }  
	                }  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
	            }  
	        }  
	  
	        return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value)
			throws ConverterException {
		 if (value == null || value.equals("")) {  
	            return "";  
	        } else {  
	            return String.valueOf(((Questionario) value).getId());  
	        } 
	}

}
