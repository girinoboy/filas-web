package br.com.managedbeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import org.primefaces.context.RequestContext;

import br.com.dao.UsuarioDAO;
import br.com.models.GuestPreferences;
import br.com.models.ThemeSwitcherBean;
import br.com.models.Usuario;


@ManagedBean
@ViewScoped
public class LoginManagedBean {
	
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@ManagedProperty(value = "#{guestPreferences}")
	GuestPreferences gp;
	 public void setGp(GuestPreferences gp) {  
	        this.gp = gp;  
	    } 
	public String login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
        usuario = usuarioDAO.verificaLoginSenha(usuario);
        if(usuario.getTema() != null){
            loggedIn = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario.getLogin());
            context.addCallbackParam("theme", "blitzer");
           
            gp.setTheme(usuario.getTema());
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
        }
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);
		return "ok";  
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
