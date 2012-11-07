package br.com.utility;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.models.Usuario;

public class FacesUtils {

	public static boolean existeUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
		if(usuario != null){
			return true;
		}else{
			return false;
		}
	}

	public static Usuario getUsuarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
		return usuario;
	}

}
