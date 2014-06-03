/**
 * 
 */
package br.com.utility;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
public class FilterPhaseListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -666895419021337416L;

	private String pagina = null;
	/**
	 * 
	 */
	public FilterPhaseListener() {
	}

	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		String viewId = fc.getViewRoot().getViewId();
		//NavigationHandler nh = fc.getApplication().getNavigationHandler();
		try {
			if (viewId != null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				UsuarioDTO usuario = ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
				String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
				
				if(usuario != null){
					if(pagina.equals("/"+Constantes.PAGINA_LOGIN)){//caso esteja autenticado redireciona para index
//						session.removeAttribute("usuarioAutenticado");
//						session.removeAttribute("indexController");
//						nh.handleNavigation(fc, null, "pretty:login");
						FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
					}
				}else if(!pagina.equals("/"+Constantes.PAGINA_LOGIN)){
					session.removeAttribute("usuarioAutenticado");
					session.removeAttribute("indexController");
					//nh.handleNavigation(fc, null, "pretty:acessoNegado");
					FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_LOGIN);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
