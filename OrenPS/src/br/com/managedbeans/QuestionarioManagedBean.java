package br.com.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import org.primefaces.push.PushContextFactory;

import br.com.dao.OpcaoDAO;
import br.com.dao.QuestaoDAO;
import br.com.dao.RespostaDAO;
import br.com.models.Opcao;
import br.com.models.Questao;
import br.com.models.Questionario;
import br.com.models.Resposta;
import br.com.models.Usuario;
import br.com.regrasdenegocio.QuestaoRN;

@ManagedBean(name="questionario")
@ViewScoped
public class QuestionarioManagedBean
implements Serializable
{
	private static final long serialVersionUID = 930035270802431115L;
	private QuestaoRN questao = new QuestaoRN();
	private List<QuestaoRN> questoes;
	private Questionario questionario;
	private DataModel<QuestaoRN> dataModelQuestoes;
	private static Map<String, Object> opcoes = new LinkedHashMap<String, Object>();
	private String resposta1;
	private String resposta2;
	private String resposta3;
	private String resposta4;
	private List<String> respostas;
	private QuestaoDAO questaoDAO = new QuestaoDAO();
	private OpcaoDAO opcaoDAO = new OpcaoDAO();
	private RespostaDAO respostaDAO = new RespostaDAO();
	private Resposta respostaBean = new Resposta();

	//@SessionScoped so chama o construtor uma unica vez
	//@RequestScoped  chama o construtor toda hora xD
	public QuestionarioManagedBean() {
		carregaQuestao();
	}


	private void carregaQuestao() {
		try{
			/*
			Object objEnvio = new Object();  
		      
		    //Para enviar ao mapa da sessão.  
		    FacesContext contexto = FacesContext.getCurrentInstance();  
		    contexto.getExternalContext().getSessionMap().put("meuObjeto", objEnvio);  
		      
		    //Para pegar do mapa da sessão.  
		    Object objRecebimento = contexto.getExternalContext().getSessionMap().get("meuObjeto");  
		    */
			if(questionario==null){
				questionario = new Questionario();
			}//FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
			if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.id") != null){
				questionario.setTitulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.titulo"));
				questionario.setId(Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.id")));
			}else if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("questionario.id") != null){
				questionario.setTitulo(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("questionario.titulo").toString());
				questionario.setId(Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("questionario.id").toString()));
				//TODO Mudar para flash
			}//PushContextFactory.getDefault().getPushContext();
			questoes = new ArrayList<QuestaoRN>();

			questaoDAO.listarPorQuestionario(questionario.getId());
			//this.questao = this.questao.getQuestao() criarQuestaoModelo(getQuestionario());
			for(Questao questao : questaoDAO.listarPorQuestionario(questionario.getId())){
				this.questao = new QuestaoRN();

				this.questao.getQuestao().setQuestionario(questionario);
				this.questao.setQuestao(questao);
				this.questao.getQuestao().setOpcoes(opcaoDAO.listByIdQuestao(questao.getId()));

				this.questoes.add(this.questao);
			}
			dataModelQuestoes = new ListDataModel<QuestaoRN>(this.questoes);

			respostas = new ArrayList<String>();

		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}


	public QuestaoRN novaQuestao()	{
		QuestaoRN q =null;
		try {
			q = questao.criarQuestaoModelo(questionario);
			//q.getQuestao().getOpcoes().add(new Opcao());
			q.setModelOpcao(new ListDataModel(q.getQuestao().getOpcoes()));
			//questao.getQuestao().setQuestionario(questionario);

		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}
		return q;
	}

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

	public void escolhaTipoDeQuestao(ValueChangeEvent event)
	{
		questao.getQuestao().setTipoDeQuestao(new Integer(event.getNewValue().toString()).intValue());
	}

	public void editarQuestao(ActionEvent event)
	{
		questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());//pega todos os dados da linha
		questao.getQuestao().setEditavel(true);//mostra o formulario de edição
		questao = new QuestaoRN();//
	}

	public void salvarQuestao(ActionEvent event) {
		try {
			questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
			questao = questao.salvarQuestao(this.questao);
			for (Opcao item : questao.getQuestao().getOpcoes()) {
				opcaoDAO.delete(item);
			}

			questao.setQuestao(questaoDAO.save(questao.getQuestao()));
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
		this.questao = new QuestaoRN();
	}

	public void salvarRespostas(ActionEvent event){

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
			String resposta = null;
			Opcao opcaoEscolhida = null;
			for (QuestaoRN questao : dataModelQuestoes) {

				switch (questao.getQuestao().getTipoDeQuestao()) {
				case 1://<!-- Questao de Texto -->
					resposta = resposta1;
					break;
				case 2://<!-- Questao de Multipla escolha -->
					resposta = resposta2;
					break;
				case 3://<!-- Questao de Unica escolha -->
					for (Opcao opcoes : questao.getQuestao().getOpcoes()) {
						if(resposta3 != null && opcoes.getCampo().equals(resposta3)){
							opcaoEscolhida = opcoes;
						}
					}
					resposta = resposta3;
					break;
				case 4://<!-- rating -->
					resposta = resposta4;
					break;

				default:
					resposta ="";
					break;
				}
				System.out.println(respostas);
				respostaBean.setOpcao(opcaoEscolhida);
				respostaBean.setQuestionario(questionario);
				if(questao.getQuestao().getTipoDeQuestao() == 2 ){
					resposta = respostas.toString();
				}
				respostaBean.setResposta(resposta);
				respostaBean.setUsuario(usuario);
				respostaDAO.save(respostaBean);
			}

		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}

		addMessage("Respostas salvas com sucesso.");
	}

	public void adicionarQuestao(ActionEvent event) {
		questoes.add(novaQuestao());
	}

	public void excluirQuestao(ActionEvent event) {
		try {
			questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
			questaoDAO.delete(questao.getQuestao());
			questoes.remove(questao);
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public void adicionarOpcao(ActionEvent event) {
		questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
		questao.adicionarOpcao(this.questao);
		questao = new QuestaoRN();
	}

	public void removerOpcao(ActionEvent event) {
		try {
			questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
			questao.removerOpcao(this.questao, this.questao.getModelOpcao().getRowIndex());
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public DataModel<QuestaoRN> getDataModelQuestoes() {
		//carretaQuestao();
		return dataModelQuestoes;
	}

	public void setDataModelQuestoes(DataModel<QuestaoRN> dataModelQuestoes) {
		this.dataModelQuestoes = dataModelQuestoes;
	}

	public Map<String, Object> getOpcoes() {
		return opcoes;
	}

	public QuestaoRN getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoRN questao) {
		this.questao = questao;
	}

	public List<QuestaoRN> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoRN> questoes) {
		this.questoes = questoes;
	}

	public String getResposta1() {
		return resposta1;
	}


	public void setResposta1(String resposta1) {
		this.resposta1 = resposta1;
	}


	public String getResposta2() {
		return resposta2;
	}


	public void setResposta2(String resposta2) {
		this.resposta2 = resposta2;
	}


	public String getResposta3() {
		return resposta3;
	}


	public void setResposta3(String resposta3) {
		this.resposta3 = resposta3;
	}


	public String getResposta4() {
		return resposta4;
	}


	public void setResposta4(String resposta4) {
		this.resposta4 = resposta4;
	}

	public List<String> getRespostas() {
		return this.respostas;
	}

	public void setRespostas(List<String> respostas) {
		this.respostas = respostas;
	}

	static
	{
		opcoes.put("Texto", Integer.valueOf(1));
		opcoes.put("Múltipla escolha", Integer.valueOf(2));
		opcoes.put("Única escolha", Integer.valueOf(3));
		opcoes.put("Estrela", Integer.valueOf(4));
	}

	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		if(questionario==null){
			questionario = new Questionario();
		}else if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.id") != null){
			//			dataModelQuestoes = new ListDataModel<QuestaoRN>();
			carregaQuestao();
		}
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
}