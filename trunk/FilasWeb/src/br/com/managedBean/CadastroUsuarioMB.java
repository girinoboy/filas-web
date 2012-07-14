package br.com.managedBean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import br.com.config.HibernateUtil;
import br.com.tables.Usuario;

@ManagedBean(name = "cadastroUsuarioMB")
public class CadastroUsuarioMB {

	private Usuario usuario;
	
	public CadastroUsuarioMB(){
		usuario = new Usuario();
	}

	public String load(){
		return "load.fwd";
	}

	public String cadastrar(){
		FacesMessage message;
		try{

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			if(usuario.getAtivoInativo() == null)
				usuario.setAtivoInativo(true);
			if(usuario.getDataNascimento() == null)
				usuario.setDataNascimento(new Date());
			if(usuario.getCpf()==null)
				usuario.setCpf("00000000000");
			session.save(usuario);

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

	public Usuario getUsuario() {
		if(usuario == null){
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuarioDTO) {
		this.usuario = usuarioDTO;
	}


}
