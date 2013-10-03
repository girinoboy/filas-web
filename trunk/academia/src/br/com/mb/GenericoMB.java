package br.com.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dto.UsuarioDTO;

public class GenericoMB {
	
	public UsuarioDTO getUserSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
	}
	
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

}
