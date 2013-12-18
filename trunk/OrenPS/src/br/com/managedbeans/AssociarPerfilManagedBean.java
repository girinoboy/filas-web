/**
 * 
 */
package br.com.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;

import br.com.dao.PerfilDAO;
import br.com.dao.UsuarioPerfilDAO;
import br.com.models.PerfilDTO;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class AssociarPerfilManagedBean {

	private PerfilDAO perfilDAO = new PerfilDAO();

	private List<PerfilDTO> perfisSmall; 

	private List<UsuarioPerfil> droppedPerfis;

	private UsuarioPerfil selectedPerfil = new UsuarioPerfil();
	
	private UsuarioPerfilDAO usuarioPerfilDAO = new UsuarioPerfilDAO();

	/**
	 * 
	 */
	public AssociarPerfilManagedBean() {
		try {
			perfisSmall = new ArrayList<PerfilDTO>();  
			droppedPerfis = new ArrayList<UsuarioPerfil>();  
			atualizaPerfis();
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
		//		populatePerfis(perfisSmall, 9);  

	}

	private void atualizaPerfis() throws Exception {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));

		perfisSmall = perfilDAO.listPerfisRestantes(usuario);
		
		droppedPerfis = usuarioPerfilDAO.listPerfisUsuario(usuario);
	}

	/*
	private void populatePerfis(List<Perfil> list, int size) {  
		for(int i = 0 ; i < size ; i++)  
			list.add(new Perfil(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
	}  */


	public void onPerfilDrop(DragDropEvent ddEvent) {  
		PerfilDTO perfil = ((PerfilDTO) ddEvent.getData());  

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
			
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setPerfil(perfil);
			
			usuarioPerfilDAO.save(usuarioPerfil);
			
			atualizaPerfis();
			//droppedPerfis.add(perfil);
			//perfisSmall.remove(perfil);
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}  

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}
	
	public void removerPerfil(ActionEvent event){
		
		try {
			if(selectedPerfil==null){
				addMessage("");
			}
			
			
//			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//			Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
			
//			UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
//			usuarioPerfil.setUsuario(usuario);
//			usuarioPerfil.setPerfil(selectedPerfil);
			usuarioPerfilDAO.delete(selectedPerfil);//precisa do id ¬¬
			//usuarioPerfilDAO.deleteItem(selectedPerfil);
			atualizaPerfis();
//			if(selectedPerfil.getId() !=null){
//				droppedPerfis.remove(selectedPerfil);
//				perfisSmall.add(selectedPerfil);
//			}
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the perfisSmall
	 */
	public List<PerfilDTO> getPerfisSmall() {
		return perfisSmall;
	}

	/**
	 * @param perfisSmall the perfisSmall to set
	 */
	public void setPerfisSmall(List<PerfilDTO> perfisSmall) {
		this.perfisSmall = perfisSmall;
	}

	/**
	 * @return the droppedPerfis
	 */
	public List<UsuarioPerfil> getDroppedPerfis() {
		return droppedPerfis;
	}

	/**
	 * @param droppedPerfis the droppedPerfis to set
	 */
	public void setDroppedPerfis(List<UsuarioPerfil> droppedPerfis) {
		this.droppedPerfis = droppedPerfis;
	}

	/**
	 * @return the selectedPerfil
	 */
	public UsuarioPerfil getSelectedPerfil() {
		return selectedPerfil;
	}

	/**
	 * @param selectedPerfil the selectedPerfil to set
	 */
	public void setSelectedPerfil(UsuarioPerfil selectedPerfil) {
		this.selectedPerfil = selectedPerfil;
	} 

}
