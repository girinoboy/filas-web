package br.com.utility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;
  
@FacesConverter(value="usuario")
public class UsuarioConverter implements Converter {  
  
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    
//    public static List<UsuarioDTO> usuarioDB;  
//  
//    static {  
//        usuarioDB = new ArrayList<UsuarioDTO>();  
//        try {
//			usuarioDB = usuarioDAO.list();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                /*
                for (UsuarioDTO u : usuarioDB) {
                    if (u.getId() == number) {
                        return u;
                    }
                }*/
                
                return usuarioDAO.getById(number);
  
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user")); 
            } catch (Exception e) {
            	throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user"));
			}
        }
  
    }
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((UsuarioDTO) value).getId());  
        }  
    }  
}  
