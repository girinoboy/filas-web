/**
 * 
 */
package br.com.managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import br.com.dao.AnexoDAO;
import br.com.models.Anexo;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class AnexoMB {
	private List<Anexo> listAnexos;
	private AnexoDAO anexoDAO = new AnexoDAO();

	/**
	 * 
	 */
	public AnexoMB() {
		try {
			listAnexos = anexoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        /*
      //save image into database
    	File file = new File("C:\\mavan-spring-hibernate-annotation-mysql.gif");
        byte[] bFile = new byte[(int) file.length()];
        
        try {
	        FileInputStream fileInputStream = new FileInputStream(file);
	        //convert file into array of bytes
	        fileInputStream.read(bFile);
	        fileInputStream.close();
        } catch (Exception e) {
	        e.printStackTrace();
        }
        */
        Anexo anexo = new Anexo();
        anexo.setNome(event.getFile().getFileName());
        anexo.setAnexo(event.getFile().getContents());
        anexo.setTamanho(event.getFile().getSize());
        anexo.setContentType(event.getFile().getContentType());
        
        try {
			anexoDAO.save(anexo);
			listAnexos = anexoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public List<Anexo> getListAnexos() {
		return listAnexos;
	}

	public void setListAnexos(List<Anexo> listAnexos) {
		this.listAnexos = listAnexos;
	} 

}
