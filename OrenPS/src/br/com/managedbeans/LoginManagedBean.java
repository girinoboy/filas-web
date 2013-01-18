package br.com.managedbeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

import br.com.dao.UsuarioDAO;
import br.com.models.GuestPreferences;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;
import br.com.utility.Constantes;


@ManagedBean
@ViewScoped
public class LoginManagedBean {

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;

	@ManagedProperty(value = "#{guestPreferences}")
	GuestPreferences gp;

	public LoginManagedBean(){}

	public String login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		String retorno = "ok";
		
		try{  
			usuario = usuarioDAO.verificaLoginSenha(usuario);
			if(usuario.getTema() != null){
				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario.getLogin());
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sessão caso ñ exista - false retorna nulo caso ñ exista
				session.setAttribute("usuarioAutenticado", usuario);

				gp.setTheme(usuario.getTema());
			} else {  
				loggedIn = false;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);  
			context.addCallbackParam("loggedIn", loggedIn);
			
		}catch(Exception e){
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Erro no banco");  
			FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
		for(UsuarioPerfil a : usuario.getUsuarioPeril()){
			if(a.getPerfil().getDescricao().equals("Visitante")){
				FacesContext.getCurrentInstance()
	            .getExternalContext()
	            .getSessionMap()
	            .put("questionario.id","1"); 
				
				FacesContext.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.put("questionario.titulo","teste");
				
				retorno = "visitante";
			}
		}
		context.addCallbackParam("perfil", retorno);
		return retorno;  
	}

	public void logout() {

		try {

			FacesContext ctx = FacesContext.getCurrentInstance();

			session = (HttpSession) ctx.getExternalContext().getSession(false);

			session.removeAttribute("usuarioAutenticado");
			//session.setAttribute("usuarioAutenticado", null);
			//session.setAttribute("usuarioSession", null);
			//session.setAttribute("indexController", null);

			ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_INDEX);

			session.invalidate();

		} catch (Exception e) {

		}

	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setGp(GuestPreferences gp) {  
		this.gp = gp;  
	} 

}
