package br.com.managedbeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.models.Usuario;

public class GenericoMB {
	
	public Usuario getUserSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((Usuario) session.getAttribute("usuarioAutenticado"));
	}

}
