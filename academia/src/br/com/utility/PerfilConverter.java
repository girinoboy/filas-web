package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.PerfilDAO;
import br.com.dto.PerfilDTO;
  
@FacesConverter(value="perfil")
public class PerfilConverter implements Converter {  
  
	private PerfilDAO perfilDAO = new PerfilDAO();
    
//	public static List<PerfilDTO> perfilDB;  
  
//    static {
//        perfilDB = new ArrayList<PerfilDTO>();  
//        try {
//			perfilDB = perfilDAO.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
  @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.equals("null") ||submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                /*
                for (PerfilDTO p : perfilDB) {
                    if (p.getId() == number) {
                        return p;
                    }
                }*/
                return perfilDAO.getById(number);
  
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user")); 
            } catch (Exception e) {
            	throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user"));
			}
        }
  
    }
  @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((PerfilDTO) value).getId());  
        }  
    }  
}  
