/**
 * 
 */
package br.com.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dao.AnexoDAO;
import br.com.dao.FrequenciaDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.FrequenciaDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioMB extends GenericoMB{

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private List<UsuarioDTO> listUsuario;
	private List<UsuarioDTO> filteredUsuarios;
	private List<String> photos = new ArrayList<String>(); 
	private AnexoDAO anexoDAO = new AnexoDAO();
	private AnexoDTO anexoDTO = new AnexoDTO();
	private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
	private FrequenciaDTO frequenciaDTO = new FrequenciaDTO();

	/**
	 * 
	 */
	public UsuarioMB() {
		try{
			listUsuario = usuarioDAO.list();
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public StreamedContent getDynamicImage() {
		byte[] emptyImage = new byte[0];
		try{
			String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");
			//String filterValue = (String) FacesContext.getCurrentInstance().getAttributes().get("image_id");

			if (id != null && !id.equals("") && this.listUsuario != null && !this.listUsuario.isEmpty()) {
				Long imagemId = Long.parseLong(id);
				for (UsuarioDTO imgTemp : this.listUsuario) {
					Integer idImage = imgTemp.getAnexoDTO().getId();
					if (idImage !=null && idImage == imagemId.intValue() && imgTemp.getAnexoDTO().getAnexo() != null) {
						//return imgTemp.getInputImage();
						return new DefaultStreamedContent(new ByteArrayInputStream(imgTemp.getAnexoDTO().getAnexo()),"image/png");
					}
				}
			}else
				return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}

		return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
	}

	private String getRandomImageName() {  
		int i = (int) (Math.random() * 10000000);  

		return String.valueOf(i);  
	}  

	public List<String> getPhotos() {  
		return photos;  
	}      

	public void oncapture(CaptureEvent captureEvent) {
		String photo = getRandomImageName();
		this.photos.add(0,photo);
		byte[] data = captureEvent.getData();

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
		String newFileName = servletContext.getRealPath("") + File.separator + "photocam" + File.separator + photo + ".png";  

		FileImageOutputStream imageOutput;
		try {
			File file = new File(newFileName);
			imageOutput = new FileImageOutputStream(file);
			imageOutput.write(data, 0, data.length);
			imageOutput.close();

			String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form1:idUsuario");
			if(!idUsuario.equals("")){
				usuarioDTO.setId(Integer.valueOf(idUsuario));
			}
			anexoDTO.setUsuarioDTO(usuarioDTO);
			anexoDAO.save(anexoDTO);

			usuarioDTO = usuarioDAO.save(usuarioDTO);
			usuarioDTO.getAnexoDTO().setNome(file.getName());
			usuarioDTO.getAnexoDTO().setAnexo(data);
			usuarioDTO.getAnexoDTO().setTamanho(file.length());
			usuarioDTO.getAnexoDTO().setContentType("png");
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			//usuarioDTO.setNome("teste");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new FacesException("Error in writing captured image.");  
		}
	}
	public void saveUsuario(){
		System.out.println(1);
	}
	public void saveUsuario(ActionEvent event){
		try {

			usuarioDAO = new UsuarioDAO();
			if(usuarioDTO.getId() !=null){
				usuarioDTO.setAnexoDTO(usuarioDAO.getById(usuarioDTO.getId()).getAnexoDTO());
			}
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			//			usuarioPerfilDAO = new UsuarioPerfilDAO();

			//			UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
			//			usuarioPerfil.setUsuario(usuarioDTO);
			//			
			//			usuarioPerfil.getPerfil().setId(Constantes.ID_PERIL_PADRAO);
			//			//atribui perfil padrão para o novo usuario.
			//			usuarioPerfilDAO.save(usuarioPerfil);
			addMessage("Salvo.");
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
		usuarioDTO = (UsuarioDTO) usuarioDAO.listById(usuarioDTO.getId());
		context.addCallbackParam("salvo", true);

		return "editar";
	}

	public String editUser(SelectEvent event) throws Exception {  
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDTO = usuarioDAO.getById(usuarioDTO.getId());
		context.addCallbackParam("salvo", true);

		return "editar";
	}

	public void delUser(ActionEvent actionEvent){
		try {
			if(usuarioDTO !=null && usuarioDTO.getId() !=null){
				usuarioDAO.delete(usuarioDTO);
				listUsuario = usuarioDAO.list();
				addMessage("Apagado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFrequecia(ActionEvent actionEvent){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("salvo", false);
			Map<String, Object> filtrosConsulta = new HashMap<>();
			filtrosConsulta.put("dataEntrada", toDateOnly(new Date()));
			filtrosConsulta.put("usuarioDTO.id", usuarioDTO.getId());

			List<FrequenciaDTO> f = frequenciaDAO.listCriterio(null, filtrosConsulta , 1);
			if(!f.isEmpty() && f.get(0) !=null){
				addMessage("Usuario já marcardo na folha de frequencia.");
			}else{
				frequenciaDTO.setUsuarioDTO(usuarioDTO);
				frequenciaDTO.setDataEntrada(toDateOnly(new Date()));
				frequenciaDAO.save(frequenciaDTO);
				context.addCallbackParam("salvo", true);
				addMessage("Salvo.");
			}
			listUsuario = usuarioDAO.list();
			//usuarioDTO = new UsuarioDTO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date toDateOnly(Date date){
		// ignora informação de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	} 

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public List<UsuarioDTO> getListUsuario() {
		return listUsuario;
	}
	public void setListUsuario(List<UsuarioDTO> listUsuario) {
		this.listUsuario = listUsuario;
	}
	public List<UsuarioDTO> getFilteredUsuarios() {
		return filteredUsuarios;
	}
	public void setFilteredUsuarios(List<UsuarioDTO> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

}
