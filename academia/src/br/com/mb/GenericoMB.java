package br.com.mb;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

public class GenericoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	protected ResourceBundle rb;
	private HttpSession session;
	@ManagedProperty(value = "#{guestPreferences}")
	protected GuestPreferences gp;

	public GenericoMB() {
		FacesContext fc = FacesContext.getCurrentInstance();
		session = (HttpSession) fc.getExternalContext().getSession(false);//true cria sess�o caso � exista - false retorna nulo caso � exista
		try {
			if(getUserSession() == null || (getUserSession() !=null && gp != null && getUserSession().getTema() != gp.getTheme())){
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setUsuario("admin");
				usuarioDTO.setSenha("admin");
				
				usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);
				
				session.setAttribute("usuarioAutenticado", usuarioDTO);
			}
			if(fc.getViewRoot() != null)
				rb = ResourceBundle.getBundle("br.com.messages.messages",fc.getViewRoot().getLocale());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UsuarioDTO getUserSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void setGp(GuestPreferences gp) {  
		this.gp = gp;  
	} 
}
