/**
 * 
 */
package br.com.utility;

import java.io.Reader;
import java.util.List;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author Marcle�nio
 *
 */
public class LerDadosXML {
	@SuppressWarnings("unused")
	private List<UsuarioDTO> listUsuario;  
	public List<UsuarioDTO> lerXml(Reader fonte){
		XStream stream = new XStream(new DomDriver());
		stream.alias("Usuario", UsuarioDTO.class);
		@SuppressWarnings("unchecked")
		List<UsuarioDTO> fromXML = (List<UsuarioDTO>) stream.fromXML(fonte);  
		popularData(fromXML);
		return fromXML;
	}

	public void popularData(List<UsuarioDTO> listUsuario){
		try {
			new UsuarioDAO().create(listUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
