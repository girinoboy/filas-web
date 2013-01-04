/**
 * 
 */
package br.com.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.models.Usuario;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioManagedBean {
	
	private Usuario usuarioBean;

	/**
	 * 
	 */
	public UsuarioManagedBean() {
		System.out.println(3);
	}
	
	
	public void saveUsuario(){
		System.out.println(1);
		
	}
	
	public void saveUsuario(ActionEvent event){
		addMessage("Salvo.");
	}
	
	
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

	public void reset() {  
        RequestContext.getCurrentInstance().reset("form:panel");  
    } 

	/**
	 * @return the usuarioBean
	 */
	public Usuario getUsuarioBean() {
		if(usuarioBean == null){
			usuarioBean = new Usuario();
		}
		return usuarioBean;
	}


	/**
	 * @param usuarioBean the usuarioBean to set
	 */
	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	

}
