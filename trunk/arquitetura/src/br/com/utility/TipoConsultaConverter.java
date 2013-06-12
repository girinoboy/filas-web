/**
 * 
 */
package br.com.utility;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dto.TipoConsultaDTO;
  
/**
 * @author marcleonio.medeiros
 *
 */

@FacesConverter(value="tipoConsulta")
public class TipoConsultaConverter implements Converter {  
  
    public static List<TipoConsultaDTO> listTipoConsulta;  
    
    static {  
    	listTipoConsulta = new ArrayList<TipoConsultaDTO>();  
        
        listTipoConsulta.add(new TipoConsultaDTO("Primeira Consulta","I"));
		listTipoConsulta.add(new TipoConsultaDTO("Retorno","R"));
		listTipoConsulta.add(new TipoConsultaDTO("Sessão","S"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico(Médico/Odontológico)","E"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico Médico","M"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico Odontológico","O"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Admissional","A"));
		listTipoConsulta.add(new TipoConsultaDTO("Perícia","P"));
    }  
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
        if (submittedValue.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(submittedValue);  
  
                for (TipoConsultaDTO p : listTipoConsulta) {  
                    if (p.getId() == number) {  
                        return p;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
            }  
        }  
  
        return null;  
    }  
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((TipoConsultaDTO) value).getId());  
        }  
    }  
}  

