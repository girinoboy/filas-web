package br.com.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import org.apache.commons.lang.StringUtils;

import br.com.dao.MenuDAO;
import br.com.models.Menu;
import br.com.models.PermissaoMenu;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;




/**
 *
 * @author Marcleonio
 */
@ManagedBean
@ViewScoped
public class IndexController {

	private MenuModel menuModel;
	private MenuDAO menuDAO = new MenuDAO();
	private Menu menu = new Menu();
	private List<Menu>  menusPermitidos = new ArrayList<Menu>();

	public IndexController(){
		geraMenu();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPeril()) {
			for (PermissaoMenu permissaoMenu : usuarioPerfil.getPerfil().getPermissaoMenu()) {
				menusPermitidos.add(permissaoMenu.getMenu());
			}
		}

		if(menu != null && menu.getPagina() == null){
			menu.setPagina("NewFile.xhtml");
		}else{
			menu = new Menu();
			menu.setPagina("NewFile.xhtml");
		}

	}

	public void geraMenu() {
		try{
			menuModel = new DefaultMenuModel();
			List<Menu> listaMenu = menuDAO.listCabecalho(); // Substituir o listaTodos() pelos menus permitidos
			for (Menu menu : listaMenu) {

				if(StringUtils.isBlank(menu.getUrl()) && StringUtils.isBlank(menu.getPagina()) ){
					Submenu submenu = new Submenu();
					submenu.setLabel(menu.getDescricao());
					geraMenu(menu,submenu);
					menuModel.addSubmenu(submenu);
				}else{
					MenuItem mi = new MenuItem();
					mi.setValue(menu.getDescricao());
					if(StringUtils.isNotBlank(menu.getIconeNativo())){
						mi.setIcon(menu.getIconeNativo());
					}else if(StringUtils.isNotBlank(menu.getIcone())){
						mi.setIcon("img/" + menu.getIcone());
					}

					if(StringUtils.isNotBlank(menu.getPagina())){
						//FacesContext context = FacesContext.getCurrentInstance();
						ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
						ELContext elContext = FacesContext.getCurrentInstance().getELContext();
						MethodExpression methodExpression = factory.createMethodExpression(elContext,"#{indexController.target}", Object.class, new Class[] {});
						//mi.addActionListener(new MethodExpressionActionListener(methodExpression));
						mi.setActionExpression(methodExpression);

						UIParameter param = new UIParameter();
						param.setName("pagina");
						param.setValue(menu.getPagina());
						mi.getChildren().add(param);

						param = new UIParameter();
						param.setName("idMenu");
						param.setValue(menu.getId());
						mi.getChildren().add(param);

						mi.setUpdate(":corpoMenuDinamico");
					}
					if(StringUtils.isNotBlank(menu.getUrl())){
						mi.setUrl(menu.getUrl());
					}
					menuModel.addMenuItem(mi);

				}


			}
		}catch(Exception e){
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public Submenu geraMenu(Menu menu, Submenu submenu) throws Exception{
		List<Menu> listaMenu = menuDAO.listByIdSub(menu.getId());
		for(Menu m:listaMenu){
			if(StringUtils.isBlank(m.getUrl()) && StringUtils.isBlank(m.getPagina()) ){

				Submenu sm = new Submenu();
				sm.setLabel(m.getDescricao());
				sm = geraMenu(m,sm);
				submenu.getChildren().add(sm);

			}else{
				MenuItem mi = new MenuItem();
				mi.setValue(m.getDescricao());
				if(StringUtils.isNotBlank(m.getIconeNativo())){
					mi.setIcon(m.getIconeNativo());
				}else if(StringUtils.isNotBlank(m.getIcone())){
					mi.setIcon("img/" + m.getIcone());
				}

				if(StringUtils.isNotBlank(m.getPagina())){
					//FacesContext context = FacesContext.getCurrentInstance();
					ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
					ELContext elContext = FacesContext.getCurrentInstance().getELContext();
					MethodExpression methodExpression = factory.createMethodExpression(elContext,"#{indexController.target}", Object.class, new Class[] {});
					//mi.addActionListener(new MethodExpressionActionListener(methodExpression));
					mi.setActionExpression(methodExpression);

					UIParameter param = new UIParameter();
					param.setName("pagina");
					param.setValue(m.getPagina());
					mi.getChildren().add(param);

					param = new UIParameter();
					param.setName("idMenu");
					param.setValue(m.getId());
					mi.getChildren().add(param);

					mi.setUpdate(":corpoMenuDinamico");
				}
				if(StringUtils.isNotBlank(m.getUrl())){
					mi.setUrl(m.getUrl());
				}
				submenu.getChildren().add(mi);
			}
		}
		return submenu;


	}
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}  

	public void target(ActionEvent actionEvent){
		try{
			String pagina = (String) actionEvent.getComponent().getAttributes().get("pagina");
			if(pagina != null){
				menu.setPagina(pagina);			
				//System.out.println("url: "+pagina);
			}else{
				addMessage(pagina);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void target(){
		try{
			String pagina = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pagina");
			String idMenu = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMenu");
			if(pagina != null){

				if(idMenu == null|| !isPermitido(Long.parseLong(idMenu))){
					menu.setPagina("acessoNegado.xhtml");
					//menu.setDescricao("Acesso Negado");
				}else{
					menu.setPagina(pagina);
					menu.setId(Long.parseLong(idMenu));
				}
				//System.out.println("pagina: "+menu.getUrl());
			}else{
				addMessage(pagina);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private boolean isPermitido(Long idMenu) {
		boolean retorno = false;
		for (Menu m : menusPermitidos) {
			if (m.getId() !=null && idMenu != null && m.getId().equals(idMenu)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	public MenuModel getMenuModel() {
		return menuModel;

	}

	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}