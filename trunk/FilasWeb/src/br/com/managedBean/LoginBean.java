package br.com.managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.config.HibernateUtil;
import br.com.tables.UsuarioDTO;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

	private String	usuario;
	private String	senha;
	private UsuarioDTO usuarioDTO;
	
	public LoginBean(){
		usuarioDTO = new UsuarioDTO();
	}

	public String verificaLogin() {

		if (this.usuario.equalsIgnoreCase("Rafael") && this.senha.equalsIgnoreCase("123")) {
			System.out.println("Logar");
			return "logar";

		} else {
			System.out.println("fail");
			return "fail";
		}
	}

	public String logar() {
		FacesMessage message;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		
		session.beginTransaction();
		
		//Usuario amigo = (Usuario)session.load(Usuario.class,1l);
		//System.out.println(amigo.getLogin());
		//session.getTransaction().commit();
		
		//LoginDAO dao = new LoginDAO();   
		// dao.retrieve(1L);  
		// dao.getLogin(usuarioDTO);
		/*
		sCadastroUsuarioAction cadastro = new CadastroUsuarioAction();
    	
    	Usuario usuario = new Usuario();
    	usuario.setLogin("marcleonio");
    	usuario.setSenha("123456");
    	usuario.setAtivoInativo(true);
    	usuario.setDataNascimento(new Date());
    	usuario.setCpf("11111111111");
		cadastro.setUsuario(usuario);
		
    	cadastro.cadastrar();*/
		
        Criteria b = session.createCriteria(UsuarioDTO.class);
        b.add( Restrictions.eq( "login",  usuarioDTO.getLogin()) );
        b.add( Restrictions.eq( "senha",  usuarioDTO.getSenha()) );
        List<UsuarioDTO> objetos = b.list(); 
        if(objetos.size()>0){
        	UsuarioDTO u = objetos.get(0);
        	System.out.println(u.getLogin());
        }else{
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login/Senha não encontrado",  "Tente mais tarte.");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        	return "index.fwd";
        	//throw new Exception();
        }
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}finally{
			session.close();  
		}
		
		return "logar";
	}
	
	public String deslogar(){
		
		return "index.fwd";
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
