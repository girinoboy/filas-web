/**
 * 
 */
package br.com.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
       
import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioMB {
	
	private List<UsuarioDTO> listUsuario;
	private List<UsuarioDTO> filteredUsuarios;
	private UsuarioDTO usuarioSelecionado = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	public UsuarioMB(){
		try {
			listUsuario = usuarioDAO.list();
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void addUser(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDAO.save(usuarioDTO);
		context.addCallbackParam("salvo", true);
		addMessage("Salvo.");
		usuarioDTO = new UsuarioDTO();
	}
	
	public String editUser(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDTO = (UsuarioDTO) usuarioDAO.listById(usuarioSelecionado.getId());
		context.addCallbackParam("salvo", true);
		
		return "editar";
	}
	
	public String editUser(SelectEvent event) throws Exception {  
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDTO = usuarioDAO.getById(usuarioSelecionado.getId());
		context.addCallbackParam("salvo", true);
		
		return "editar";
	}
	
	public void delUser(ActionEvent actionEvent){
		try {
			if(usuarioSelecionado !=null && usuarioSelecionado.getId() !=null){
			usuarioDAO.delete(usuarioSelecionado);
			listUsuario = usuarioDAO.list();
			addMessage("Apagado.");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reset() {  
        RequestContext.getCurrentInstance().reset("form:panel");  
    } 
	//metodo generico que envia mesagens para a tela
//	private void addMessage(FacesMessage message) {
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

	/**
	 * @return the listUsuario
	 */
	public List<UsuarioDTO> getListUsuario() {
		return listUsuario;
	}
	/**
	 * @param listUsuario the listUsuario to set
	 */
	public void setListUsuario(List<UsuarioDTO> listUsuario) {
		this.listUsuario = listUsuario;
	}
	/**
	 * @return the filteredUsuarios
	 */
	public List<UsuarioDTO> getFilteredUsuarios() {
		return filteredUsuarios;
	}
	/**
	 * @param filteredUsuarios the filteredUsuarios to set
	 */
	public void setFilteredUsuarios(List<UsuarioDTO> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}
	/**
	 * @return the usuarioSelecionado
	 */
	public UsuarioDTO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	/**
	 * @param usuarioSelecionado the usuarioSelecionado to set
	 */
	public void setUsuarioSelecionado(UsuarioDTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}


	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}


	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
