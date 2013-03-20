/**
 * 
 */
package br.com.managedbeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;

import br.com.dao.AnexoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dao.UsuarioQuestionarioDAO;
import br.com.models.Anexo;
import br.com.models.EmailBean;
import br.com.models.Questionario;
import br.com.models.Usuario;
import br.com.models.UsuarioQuestionario;
import br.com.utility.EmailUtils;
/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class EmailControleManagedBean {



	private LazyDataModel<UsuarioQuestionario> lazyModel;

	private LazyDataModel<Usuario> lazyModelCE;

	private List<Usuario> filteredCars;

	private UsuarioQuestionario selectedCar;

	private Usuario selectedEC;

	private List<UsuarioQuestionario> usuarioQuestionario;

	private static String[] colors;

	private static String[] manufacturers;  

	private UsuarioQuestionarioDAO usuarioQuestionarioDAO = new UsuarioQuestionarioDAO();

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private List<Usuario> listControleEmail;

	private static EmailBean emailBean = new EmailBean();

	private AnexoDAO anexoDAO = new AnexoDAO();

	private List<Questionario> questionariosSelecionados;

	private List<Usuario> usuariosSelecionados;

	static {  
		colors = new String[10];  
		colors[0] = "Black";  
		colors[1] = "White";  
		colors[2] = "Green";  
		colors[3] = "Red";  
		colors[4] = "Blue";  
		colors[5] = "Orange";  
		colors[6] = "Silver";  
		colors[7] = "Yellow";  
		colors[8] = "Brown";  
		colors[9] = "Maroon";  

		manufacturers = new String[10];  
		manufacturers[0] = "Mercedes";  
		manufacturers[1] = "BMW";  
		manufacturers[2] = "Volvo";  
		manufacturers[3] = "Audi";  
		manufacturers[4] = "Renault";  
		manufacturers[5] = "Opel";  
		manufacturers[6] = "Volkswagen";  
		manufacturers[7] = "Chrysler";  
		manufacturers[8] = "Ferrari";  
		manufacturers[9] = "Ford";  
	}  

	/**
	 * 
	 */
	public EmailControleManagedBean() {
		//populateRandomCars(usuarioQuestionario, 50);  

		try {
			usuarioQuestionario = usuarioQuestionarioDAO.list();
			usuarioQuestionario = usuarioQuestionarioDAO.a();
			listControleEmail =  usuarioDAO.list();
			//lazyModel = new LazyUsuarioQuestionarioDataModel(usuarioQuestionario); 
			lazyModelCE = new LazyControleEmail(listControleEmail);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	public void enviarEmail(){
		
		emailBean.setSubject("Pesquisa de satisfação");
		emailBean.setMsg("This is a test mail ... :-)");
		//enviaEmail(emailBean);
		EmailUtils emailUtils = new EmailUtils();
		Anexo anexo = new Anexo();
		try {
			//pesquisar pela img recebendo o parametro pelo tipo de questionario
			//cada questionario pode ter uma arte de apresentração diferente para ela
			//mas as quetões são as mesmas criadas pelo gestor
			for (Questionario quest : questionariosSelecionados) {
				for (Usuario user : usuariosSelecionados) {

					emailBean.setTo(user.getEmail());
					quest.getAnexo().setId(4);//TODO remover auto seção
					anexo = anexoDAO.getById(quest.getAnexo().getId());
					byte[] arquivoUpload = anexo.getAnexo();
					String nomeArquivo = anexo.getNome();

					FacesContext facesContext = FacesContext.getCurrentInstance();
					ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
					String arquivoSalvo = scontext.getRealPath("/img/upload/" + nomeArquivo);
					criaArquivo(arquivoUpload, arquivoSalvo);//cria o arquivo na pasta da aplicação

					HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
					String urlAplicacao = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
					//request.getRequestURL().substring(0,request.getRequestURL().indexOf("/"));

					emailBean.setUrlQuestionario(request.getRequestURL().toString());
					emailBean.setUrlArquivoTemplate(urlAplicacao+"/layout/templateEmail.html");
					emailBean.setUrlArte(urlAplicacao+"/img/upload/" + nomeArquivo);

					emailUtils.enviaEmailHtml(emailBean);
					addMessage("Email eviado com sucesso para: "+user.getNome());
				}
			}
				addMessage("Email(s) eviado(s) com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				addMessage("Erro ao tentar enviar e-mail: "+e.getMessage());
			}
		}

		public void addMessage(String summary) {  
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
			FacesContext.getCurrentInstance().addMessage(null, message);  
		}

		//salva arquivo na pasta da propria aplicacao
		public void criaArquivo(byte[] bytes, String arquivo) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(arquivo);
				fos.write(bytes);
				fos.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		private SelectItem[] createFilterOptions(String[] data)  {    
			SelectItem[] options = new SelectItem[data.length + 1];    

			options[0] = new SelectItem("", "Select");    
			for(int i = 0; i < data.length; i++) {    
				options[i + 1] = new SelectItem(data[i], data[i]);    
			}    

			return options;    
		}

		public UsuarioQuestionario getSelectedCar() {  
			return selectedCar;  
		}  

		public void setSelectedCar(UsuarioQuestionario selectedCar) {  
			this.selectedCar = selectedCar;  
		}  

		public LazyDataModel<UsuarioQuestionario> getLazyModel() {  
			return lazyModel;  
		}  

		private void populateRandomCars(List<UsuarioQuestionario> list, int size) {  
			for(int i = 0 ; i < size ; i++) {  
				list.add(new UsuarioQuestionario(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
			}  
		}  

		private String getRandomModel() {

			return String.valueOf((Math.random() * 100))  ;
		}

		private String getRandomColor() {  
			return colors[(int) (Math.random() * 10)];  
		}  

		private String getRandomManufacturer() {  
			return manufacturers[(int) (Math.random() * 10)];  
		}  

		private int getRandomYear() {  
			return (int) (Math.random() * 50 + 1960);  
		}

		public void onRowSelect(){

		}

		public List<Usuario> getListControleEmail() {
			return listControleEmail;
		}

		public void setListControleEmail(List<Usuario> listControleEmail) {
			this.listControleEmail = listControleEmail;
		}

		public LazyDataModel<Usuario> getLazyModelCE() {
			return lazyModelCE;
		}

		public void setLazyModelCE(LazyDataModel<Usuario> lazyModelCE) {
			this.lazyModelCE = lazyModelCE;
		}

		public Usuario getSelectedEC() {
			return selectedEC;
		}

		public void setSelectedEC(Usuario selectedEC) {
			this.selectedEC = selectedEC;
		}

		public List<Usuario> getFilteredCars() {
			return filteredCars;
		}

		public void setFilteredCars(List<Usuario> filteredCars) {
			this.filteredCars = filteredCars;
		}

		/**
		 * @return the questionariosSelecionados
		 */
		public List<Questionario> getQuestionariosSelecionados() {
			return questionariosSelecionados;
		}

		/**
		 * @param questionariosSelecionados the questionariosSelecionados to set
		 */
		public void setQuestionariosSelecionados(
				List<Questionario> questionariosSelecionados) {
			this.questionariosSelecionados = questionariosSelecionados;
		}

		/**
		 * @return the usuarioQuestionario
		 */
		public List<UsuarioQuestionario> getUsuarioQuestionario() {
			return usuarioQuestionario;
		}

		/**
		 * @param usuarioQuestionario the usuarioQuestionario to set
		 */
		public void setUsuarioQuestionario(List<UsuarioQuestionario> usuarioQuestionario) {
			this.usuarioQuestionario = usuarioQuestionario;
		}

		/**
		 * @return the usuariosSelecionados
		 */
		public List<Usuario> getUsuariosSelecionados() {
			return usuariosSelecionados;
		}

		/**
		 * @param usuariosSelecionados the usuariosSelecionados to set
		 */
		public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
			this.usuariosSelecionados = usuariosSelecionados;
		}

		/**
		 * @param lazyModel the lazyModel to set
		 */
		public void setLazyModel(LazyDataModel<UsuarioQuestionario> lazyModel) {
			this.lazyModel = lazyModel;
		}
	}
