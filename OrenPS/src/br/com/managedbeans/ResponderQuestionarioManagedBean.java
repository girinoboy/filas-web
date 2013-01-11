package br.com.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.RespostaDAO;
import br.com.models.Resposta;

@ManagedBean
@ViewScoped
public class ResponderQuestionarioManagedBean {

	private RespostaDAO respostaDAO = new RespostaDAO();
	private Resposta respostaBean = new Resposta();

	public void salvarRespostas(){
		
		try {
			respostaDAO.save(respostaBean);
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
		
		addMessage("Respostas salvas com sucesso.");
	}
	
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}
}
