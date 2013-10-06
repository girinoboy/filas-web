/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.PerfilDAO;
import br.com.dto.PerfilDTO;
import br.com.utility.PerfilConverter;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
@RequestScoped
public class PerfilMB extends GenericoMB{
	private PerfilDTO perfilDTO = new PerfilDTO();
	private PerfilDAO perfilDAO = new PerfilDAO();
	private List<PerfilDTO> listPerfil = new ArrayList<PerfilDTO>();
	/**
	 * 
	 */
	public PerfilMB() {
		try {
			listPerfil = //PerfilConverter.perfilDB;
			perfilDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addPerfil(ActionEvent actionEvent) throws Exception {
		perfilDAO.save(perfilDTO);
		addMessage("Salvo com sucesso.");
		perfilDTO = new PerfilDTO();
	}
	
	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}
	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public List<PerfilDTO> getListPerfil() {
		return listPerfil;
	}

	public void setListPerfil(List<PerfilDTO> listPerfil) {
		this.listPerfil = listPerfil;
	}
	
	

}
