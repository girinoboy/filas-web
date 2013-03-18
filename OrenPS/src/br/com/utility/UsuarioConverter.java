package br.com.utility;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.UsuarioDAO;
import br.com.models.Usuario;
  
@FacesConverter(value="usuario")
public class UsuarioConverter implements Converter {  
  
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    public static List<Usuario> usuarioBD;  
  /*
    static {  
        usuarioBD = new ArrayList<Usuario>();  
        try {
			usuarioBD = usuarioDAO.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  */
    public UsuarioConverter (){
    	try {
			usuarioBD = usuarioDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
        if (submittedValue.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(submittedValue);  
  
                for (Usuario p : usuarioBD) {  
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
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Usuario) value).getId());  
        }  
    }  
}  
