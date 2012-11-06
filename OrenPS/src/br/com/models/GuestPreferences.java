package br.com.models;

import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import br.com.dao.ControleAcessoDAO;
import br.com.managedbeans.IndexController;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theme = "aristo"; //default
	private String pagina = "";
	private List<Menu>  menusPermitidos = new ArrayList<Menu>();

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		if(pagina.equals("/login.xhtml")){
			theme = "aristo"; //default para pagina inicial
		}else{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Usuario usuario = ((Usuario) session.getAttribute("usuarioSession"));
			if(usuario != null){

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

			}
		}


		if(params.containsKey("theme")) {
			theme = params.get("theme");
		}

		return theme;
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

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
