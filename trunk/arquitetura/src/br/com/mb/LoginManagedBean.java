package br.com.mb;


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
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;


@ManagedBean
@ViewScoped
public class LoginManagedBean {

	private UsuarioDTO usuario = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;


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

//				gp.setTheme(usuario.getTema());
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
		String retornoA = null;
		String retornoV = null;
		if(retornoA == null && retornoV !=null){
			retorno = retornoV;
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

//			ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_INDEX);

			session.invalidate();

		} catch (Exception e) {

		}

	}

	/**
	 * @return the usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}



}
