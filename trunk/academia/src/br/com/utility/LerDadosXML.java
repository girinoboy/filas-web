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
 * @author Marcleônio
 *
 */
public class LerDadosXML {
	private List<UsuarioDTO> listUsuario;  
	public List<UsuarioDTO> lerXml(Reader fonte){
		XStream stream = new XStream(new DomDriver());
		stream.alias("Usuario", UsuarioDTO.class);
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
