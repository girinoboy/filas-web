/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.PerfilDAO;
import br.com.dto.PerfilDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
@RequestScoped
public class PerfilMB extends GenericoMB implements ModeloMB{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public void add(ActionEvent actionEvent)  {
		try {
			perfilDAO.save(perfilDTO);
			addMessage("Salvo com sucesso.");
			perfilDTO = new PerfilDTO();
			listPerfil = perfilDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(ActionEvent actionEvent) {
		try{
			perfilDAO.delete(perfilDTO);
			perfilDTO = new PerfilDTO();
			listPerfil = perfilDAO.list();
			addMessage("Apagado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}

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
