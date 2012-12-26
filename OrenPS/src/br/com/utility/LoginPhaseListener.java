package br.com.utility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.managedbeans.IndexController;
import br.com.models.Menu;
import br.com.models.PermissaoMenu;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;

public class LoginPhaseListener implements PhaseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final String PRG_DONE_ID = null;
	private List<Menu>  menusPermitidos = new ArrayList<Menu>();
	String pagina = null;
	public void afterPhase(PhaseEvent event) {/*
        FacesContext fc = event.getFacesContext();
        String viewId = fc.getViewRoot().getViewId();
        NavigationHandler nh = fc.getApplication().getNavigationHandler();        
        if (viewId != null) {
            boolean loginPage = viewId.lastIndexOf("login") > -1 ? true : false;
            boolean indexPage = viewId.lastIndexOf("index") > -1 ? true : false;
            boolean deniedPage = viewId.lastIndexOf("acessoNegado") > -1 ? true : false;
            if (!loginPage && !loggedIn()) {
                nh.handleNavigation(fc, null, "pretty:login");
            }
            if (!loginPage && loggedIn() && !deniedPage && !indexPage) {
                if (!hasAccess(viewId)) {
                    nh.handleNavigation(fc, null, "pretty:acessoNegado");
                }
            }
        }*/
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
			String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();

			//    	ControleAcessoDAO permissaoDAO = new ControleAcessoDAO();
			//		List<PermissaoMenu> listaPermissao = permissaoDAO.listByIdsPerfil(usuario.getUsuarioPeril());
			//		for (PermissaoMenu permissao : listaPermissao) {
			//			menusPermitidos.add(permissao.getMenu());
			//		}
			if(usuario != null){
				for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPeril()) {
					for (PermissaoMenu permissaoMenu : usuarioPerfil.getPerfil().getPermissaoMenu()) {
						menusPermitidos.add(permissaoMenu.getMenu());
					}
				}
				IndexController indexcontroller = (IndexController)session.getAttribute("indexController");

				if(indexcontroller != null && !pagina.equals("/index.xhtml")  && !pagina.equals("/acessoNegado.xhtml") && !pagina.equals("/login.xhtml")){

					this.pagina = indexcontroller.getMenu().getPagina();
					indexcontroller.getMenu().setPagina("acessoNegado.xhtml");
					session.setAttribute("indexController", indexcontroller);
					//FacesContext.getCurrentInstance().getExternalContext().redirect("acessoNegado.xhtml");
					//FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.xhtml");

				}else if(pagina.equals("/login.xhtml")){//caso esteja autenticado remore a sessão
					session.removeAttribute("usuarioAutenticado");
					session.removeAttribute("indexController");
					FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
					//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				}else if(pagina.equals("/acessoNegado.xhtml")){
					session.removeAttribute("indexController");
					//session.removeAttribute("usuarioAutenticado");
				}
			}else if(!pagina.equals("/login.xhtml")){
				session.removeAttribute("usuarioAutenticado");
				session.removeAttribute("indexController");
				FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void beforePhase(PhaseEvent event) {/*
    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    	Usuario usuario = ((Usuario) session.getAttribute("usuarioSession"));
    	String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();

    	ControleAcessoDAO permissaoDAO = new ControleAcessoDAO();
		List<PermissaoMenu> listaPermissao = permissaoDAO.listByIdsPerfil(usuario.getUsuarioPeril());
		for (PermissaoMenu permissao : listaPermissao) {
			menusPermitidos.add(permissao.getMenu());
		}

		IndexController indexcontroller = (IndexController)session.getAttribute("indexController");

		if(!isPermitido(indexcontroller.getMenu().getDescricao()) && indexcontroller.getMenu().getDescricao() !=null && pagina != "/index.xhtml"){
			try {
				this.pagina = indexcontroller.getMenu().getPagina();
				indexcontroller.getMenu().setPagina("acessoNegado.xhtml");
				session.setAttribute("indexController", indexcontroller);
				FacesContext.getCurrentInstance().getExternalContext().redirect("acessoNegado.xhtml");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 */

	}



	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}/*
	private boolean loggedIn() {
		return FacesUtils.existeUsuarioLogado();
	}
	private boolean isPermitido(String descricao) {
		boolean retorno = false;
		for (Menu m : menusPermitidos) {
			if (m.getDescricao() !=null && descricao != null && m.getDescricao().equals(descricao)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	private boolean hasAccess(String viewId) {
		boolean retorno = false;
		Usuario usuarioLogado = FacesUtils.getUsuarioLogado();
		if (usuarioLogado != null) {
			List<Menu> permissoes = menusPermitidos;
			for (Menu permissao : permissoes) {
				if (viewId.equals("/" + permissao.getPagina())) {
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}*/

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}


}
