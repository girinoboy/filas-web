/**
 * 
 */
package br.com.mb;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.dao.FrequenciaDAO;
import br.com.dto.FrequenciaDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class FrequenciaMB extends GenericoMB{

	FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
	FrequenciaDTO frequenciaDTO = new FrequenciaDTO();

	/**
	 * 
	 */
	public FrequenciaMB() {
		// TODO Auto-generated constructor stub
	}


	public void addFrequecia(ActionEvent actionEvent){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("salvo", false);
			frequenciaDTO.setUsuarioDTO(null);
			frequenciaDTO.setDataEntrada(new Date());
			frequenciaDAO.save(frequenciaDTO);
			context.addCallbackParam("salvo", true);
			addMessage("Salvo.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
