/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;
import br.com.utility.UsuarioConverter;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
public class AutoCompleteMB {

	private UsuarioDTO usuarioSelecionado1;  

	private UsuarioDTO usuarioSelecionado2;
	
	private List<UsuarioDTO> listUsuario;
	
	private UsuarioDTO usuarioDTO;
	
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * 
	 */
	public AutoCompleteMB() {
		
		try {
			//players = usuarioDAO.list();
			listUsuario = UsuarioConverter.usuarioDB;
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}  

	public UsuarioDTO getUsuarioSelecionado1() {
		return usuarioSelecionado1;
	}  

	public void setUsuarioSelecionado1(UsuarioDTO usuarioSelecionado1) {
		this.usuarioSelecionado1 = usuarioSelecionado1;
	}  

	public UsuarioDTO getUsuarioSelecionado2() {
		return usuarioSelecionado2;
	}

	public void setUsuarioSelecionado2(UsuarioDTO usuarioSelecionado2) {
		this.usuarioSelecionado2 = usuarioSelecionado2;
	}  

	public List<UsuarioDTO> completarUsuario(String query) throws Exception {
		List<UsuarioDTO> suggestions = new ArrayList<UsuarioDTO>();
/*
		for(UsuarioDTO u : listUsuario) {
			if(u.getNome().startsWith(query))
				suggestions.add(u);
		}*/
		
		Map<String, Object> filtrosConsulta = new HashMap<String, Object>();
		filtrosConsulta.put("nome", query);
		suggestions = usuarioDAO.listCriterio(null, filtrosConsulta, Constantes.TIPO_CONSULTA_ILIKE);

		return suggestions;
	}

	public List<UsuarioDTO> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<UsuarioDTO> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsurioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
