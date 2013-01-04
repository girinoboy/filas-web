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
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;

import br.com.dao.PerfilDAO;
import br.com.models.Perfil;
import br.com.models.Usuario;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class AssociarPerfilManagedBean {

	private PerfilDAO perfilDAO = new PerfilDAO();

	private List<Perfil> perfisSmall; 

	private List<Perfil> droppedPerfis;

	private Perfil selectedPerfil = new Perfil();

	/**
	 * 
	 */
	public AssociarPerfilManagedBean() {
		try {
			perfisSmall = new ArrayList<Perfil>();  
			droppedPerfis = new ArrayList<Perfil>();  
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));

			perfisSmall = perfilDAO.listPerfisRestantes(usuario);
			
			droppedPerfis = perfilDAO.listPerfisUsuario(usuario);
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
		//		populatePerfis(perfisSmall, 9);  

	}

	/*
	private void populatePerfis(List<Perfil> list, int size) {  
		for(int i = 0 ; i < size ; i++)  
			list.add(new Perfil(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
	}  */


	public void onPerfilDrop(DragDropEvent ddEvent) {  
		Perfil perfil = ((Perfil) ddEvent.getData());  

		droppedPerfis.add(perfil);  
		perfisSmall.remove(perfil);  
	}  

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

	/**
	 * @return the perfisSmall
	 */
	public List<Perfil> getPerfisSmall() {
		return perfisSmall;
	}

	/**
	 * @param perfisSmall the perfisSmall to set
	 */
	public void setPerfisSmall(List<Perfil> perfisSmall) {
		this.perfisSmall = perfisSmall;
	}

	/**
	 * @return the droppedPerfis
	 */
	public List<Perfil> getDroppedPerfis() {
		return droppedPerfis;
	}

	/**
	 * @param droppedPerfis the droppedPerfis to set
	 */
	public void setDroppedPerfis(List<Perfil> droppedPerfis) {
		this.droppedPerfis = droppedPerfis;
	}

	/**
	 * @return the selectedPerfil
	 */
	public Perfil getSelectedPerfil() {
		return selectedPerfil;
	}

	/**
	 * @param selectedPerfil the selectedPerfil to set
	 */
	public void setSelectedPerfil(Perfil selectedPerfil) {
		this.selectedPerfil = selectedPerfil;
	} 

}
