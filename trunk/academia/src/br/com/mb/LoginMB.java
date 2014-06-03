/**
 * 
 */
package br.com.mb;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.dao.PerfilDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.PerfilDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;
//import br.com.dao.LocalDAO;
//import br.com.dto.LocalDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
@ViewScoped
public class LoginMB extends GenericoMB{
	
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;
//	@ManagedProperty(value = "#{guestPreferences}")
//	private GuestPreferences gp;

	/**
	 * 
	 */
	public LoginMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		boolean adm = false;
		
		try{
			usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);
			adm = usuarioDTO!=null&& usuarioDTO.getUsuario().equals("admin") && usuarioDTO.getPerfilDTO().getId().equals(1);
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sessão caso ñ exista - false retorna nulo caso ñ exista
			
			if(usuarioDTO != null && usuarioDTO.getTema() != null){
				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("welcome"), usuarioDTO.getUsuario());
				session.setAttribute("usuarioAutenticado", usuarioDTO);
//				session.setAttribute("listMenuAutenticado",usuarioDTO.getPerfilDTO().getListMenu());
				session.setAttribute("adm", adm);
				
//				gp.setTheme(usuarioDTO.getTema());
				context.execute("PF('dlg').hide();");
				FacesContext.getCurrentInstance().getExternalContext().redirect("layoutElement.xhtml");
			} else if(usuarioDTO==null && usuarioDAO.list().size()==0){
				
				//cria perfil
				PerfilDTO perfilDTO = new PerfilDAO().save(new PerfilDTO(1));
				
				//cria salas
//				new LocalDAO().save(new LocalDTO("Sala 1"));
//				new LocalDAO().save(new LocalDTO("Sala 2"));
				
				//cria usuario
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setUsuario("admin");
				usuarioDTO.setSenha("admin");
				usuarioDTO.setTema("flick");
				usuarioDTO.setNome("Administrador do Sistema");
				usuarioDTO.setDataNascimento(new Date());
				usuarioDTO.setPerfilDTO(perfilDTO);
				usuarioDTO = usuarioDAO.save(usuarioDTO);
				//criaMenus();

				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("welcome"), usuarioDTO.getUsuario());
				
				session.setAttribute("adm", adm);
				session.setAttribute("usuarioAutenticado", usuarioDTO);
//				session.setAttribute("listMenuAutenticado",usuarioDTO.getPerfilDTO().getListMenu());
//				gp.setTheme(usuarioDTO.getTema());
				context.execute("PF('dlg').hide();");
				FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
			}else{
				loggedIn = false;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"); 
				usuarioDTO = new UsuarioDTO();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Erro no banco");  
		}finally{
			context.addCallbackParam("loggedIn", loggedIn);
			context.addCallbackParam("perfil", usuarioDTO.getPerfilDTO().getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
/*
	private void criaMenus() throws Exception {
		//cria perfil
		PerfilDTO perfilDTO = new PerfilDAO().save(new PerfilDTO(1));
		
		//cria salas
//		new LocalDAO().save(new LocalDTO("Sala 1"));
//		new LocalDAO().save(new LocalDTO("Sala 2"));
		
		//cria usuario
		usuarioDTO = new UsuarioDTO();			
		usuarioDTO.setUsuario("admin");
		usuarioDTO.setSenha("admin");
		usuarioDTO.setTema("flick");
		usuarioDTO.setNome("Administrador do Sistema");
		usuarioDTO.setDataNascimento(new Date());
		usuarioDTO.setPerfilDTO(new PerfilDTO(1));
		usuarioDTO = usuarioDAO.save(usuarioDTO);
		
		//INSERT INTO USUARIO_PERFIL VALUES(1,1,1);
		new UsuarioPerfilDAO().save(new UsuarioPerfilDTO(usuarioDTO, perfilDTO));
		
		//cria menus
		PerfilMenuDAO perfilMenuDAO = new PerfilMenuDAO();
		MenuDAO menuDAO = new MenuDAO();
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setNome("exit");
		menuDTO.setComando("#{loginMB.logout}");
		menuDTO.setDropIndex(3);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);				
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));

		menuDTO = new MenuDTO();
		menuDTO.setNome("accessControl");
		menuDTO.setUrl("controleAcesso.xhtml");
		menuDTO.setDropIndex(1);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("profileRegister");
//				menuDTO.setUrl("controleAcesso.xhtml");
		menuDTO.setOutcome("cadastrarPerfil.xhtml");
		menuDTO.setDropIndex(2);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("configureMenu");
		menuDTO.setUrl("configuraMenu.xhtml");
		menuDTO.setDropIndex(1);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));

		menuDTO = new MenuDTO();
		menuDTO.setNome("manageUser");
		menuDTO.setUrl("gerenciarUsuario.xhtml");
		menuDTO.setDropIndex(0);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		
		PerfilMenuDTO perfilMenuDTO = perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		System.out.println(perfilMenuDTO);
	}*/

	public void logout() {

		try {

			FacesContext ctx = FacesContext.getCurrentInstance();

			session = (HttpSession) ctx.getExternalContext().getSession(false);

			session.removeAttribute("usuarioAutenticado");
			//session.setAttribute("usuarioAutenticado", null);
			//session.setAttribute("usuarioSession", null);
			//session.setAttribute("indexController", null);

			//ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_LOGIN);
			ctx.getExternalContext().redirect(Constantes.PAGINA_LOGIN);
			session.invalidate();

		} catch (Exception e) {

		}

	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
