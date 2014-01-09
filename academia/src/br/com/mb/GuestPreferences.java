package br.com.mb;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theme;

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		if(pagina.equals("/login.xhtml")){
			theme = "aristo"; //default para pagina inicial
		}else if(getUserSession()!=null){
			theme = getUserSession().getTema();
		}if(theme==null) {
			theme = "flick";//default
		}
		if(params.containsKey("theme")) {
			theme = params.get("theme");
		}

		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public UsuarioDTO getUserSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioDTO usuarioDTO;
		usuarioDTO = ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));

		try{
			if(usuarioDTO == null || (usuarioDTO !=null && usuarioDTO.getTema() != theme)){
				if(usuarioDTO==null){
					usuarioDTO = new UsuarioDTO();
				}
				UsuarioDAO usuarioDAO = new UsuarioDAO();

				usuarioDTO.setUsuario("admin");
				usuarioDTO.setSenha("admin");

				usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);

				session.setAttribute("usuarioAutenticado", usuarioDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioDTO;

	}

}
