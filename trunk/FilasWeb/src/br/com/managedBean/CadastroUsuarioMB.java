package br.com.managedBean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import br.com.config.HibernateUtil;
import br.com.tables.UsuarioDTO;

@ManagedBean(name = "cadastroUsuarioMB")
public class CadastroUsuarioMB {

	private UsuarioDTO usuarioDTO;
	
	public CadastroUsuarioMB(){
		usuarioDTO = new UsuarioDTO();
	}

	public String load(){
		return "load.fwd";
	}

	public String cadastrar(){
		FacesMessage message;
		try{

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			if(usuarioDTO.getAtivoInativo() == null)
				usuarioDTO.setAtivoInativo(true);
			if(usuarioDTO.getDataNascimento() == null)
				usuarioDTO.setDataNascimento(new Date());
			if(usuarioDTO.getCpf()==null)
				usuarioDTO.setCpf("00000000000");
			session.save(usuarioDTO);

			session.getTransaction().commit();

		}catch(Exception e){
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro no sistema",  "Tente mais tarte.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return "erroCadastrar.fwd";
		}
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",  "Cadastro realizado com sucessso.");

		FacesContext.getCurrentInstance().addMessage(null, message);

		return "cadastrar.fwd";
	}

	public UsuarioDTO getUsuarioDTO() {
		if(usuarioDTO == null){
			usuarioDTO = new UsuarioDTO();
		}
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}


}
