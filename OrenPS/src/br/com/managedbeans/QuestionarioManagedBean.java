package br.com.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.dao.OpcaoDAO;
import br.com.dao.QuestaoDAO;
import br.com.models.Opcao;
import br.com.models.Questionario;
import br.com.regrasdenegocio.QuestaoRN;

@ManagedBean(name="questionario")
@SessionScoped
public class QuestionarioManagedBean
implements Serializable
{
	private static final long serialVersionUID = 930035270802431115L;
	private QuestaoRN questao = new QuestaoRN();
	private List<QuestaoRN> questoes;
	private DataModel<QuestaoRN> dataModelQuestoes;
	private static Map<String, Object> opcoes = new LinkedHashMap<String, Object>();
	private String resposta;
	private List<String> respostas;
	private Questionario questionario;
	private QuestaoDAO questaoDAO = new QuestaoDAO();
	private OpcaoDAO opcaoDAO = new OpcaoDAO();

	public QuestionarioManagedBean()
	{
		try{
			questoes = new ArrayList<QuestaoRN>();
			//this.questao = new QuestaoRN(getQuestionario());
			//this.questao = this.questao.criarQuestaoModelo(getQuestionario());
			//this.questoes.add(this.questao);
			dataModelQuestoes = new ListDataModel<QuestaoRN>(this.questoes);

			respostas = new ArrayList<String>();

			getQuestionario().setTitulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.titulo"));
			getQuestionario().setId(Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.id")));
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

	public void adicionarQuestao(ActionEvent event) {
		questoes.add(novaQuestao());
	}

	public void excluirQuestao(ActionEvent event) {
		questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
		questoes.remove(questao);
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
		return this.dataModelQuestoes;
	}

	public void setDataModelQuestoes(DataModel<QuestaoRN> dataModelQuestoes) {
		this.dataModelQuestoes = dataModelQuestoes;
	}

	public Map<String, Object> getOpcoes() {
		return opcoes;
	}

	public QuestaoRN getQuestao() {
		return this.questao;
	}

	public void setQuestao(QuestaoRN questao) {
		this.questao = questao;
	}

	public List<QuestaoRN> getQuestoes() {
		return this.questoes;
	}

	public void setQuestoes(List<QuestaoRN> questoes) {
		this.questoes = questoes;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
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
			questionario.setTitulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.titulo"));
			questionario.setId(Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionario.id")));
			questao.getQuestao().setQuestionario(questionario);
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