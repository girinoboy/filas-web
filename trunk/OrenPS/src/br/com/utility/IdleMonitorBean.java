package br.com.utility;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.managedbeans.LoginManagedBean;

@ManagedBean(name = "idleBean")
public class IdleMonitorBean {

	public void welcomeListener() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back",
						"Continue your works."));

	}

	public void logoutListener()  {
		try {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"You Have Logged Out!",
							"Thank you for using abc Online Financial Services"));

			//LoginManagedBean l = new LoginManagedBean();

			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

			session.removeAttribute("usuarioAutenticado");

			// invalidate session, and redirect to other pages

			//FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
			//FacesContext ctx = FacesContext.getCurrentInstance();
			ctx .getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_INDEX);
			session.invalidate();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro",
					e.getMessage()));
			e.printStackTrace();
		}
	}
}
