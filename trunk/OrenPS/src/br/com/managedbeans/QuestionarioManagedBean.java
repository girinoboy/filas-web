package br.com.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.models.Opcao;
import br.com.regrasdenegocio.QuestaoRN;

@ManagedBean(name="questionario")
@SessionScoped
public class QuestionarioManagedBean
  implements Serializable
{
  private static final long serialVersionUID = 930035270802431115L;
  private QuestaoRN questao;
  private List<QuestaoRN> questoes;
  private DataModel dataModelQuestoes;
  private static Map<String, Object> opcoes = new LinkedHashMap<String, Object>();
  private String resposta;
  private List<String> respostas;

  public QuestionarioManagedBean()
  {
    this.questoes = new ArrayList<QuestaoRN>();
    this.questao = new QuestaoRN();
    this.questoes.add(this.questao);
    this.dataModelQuestoes = new ListDataModel(this.questoes);
    this.questao = this.questao.criarQuestaoModelo();
    this.respostas = new ArrayList<String>();
  }

  public QuestaoRN novaQuestao()
  {
    QuestaoRN q = this.questao.criarQuestaoModelo();
    q.getQuestao().getOpcoes().add(new Opcao());
    q.setModelOpcao(new ListDataModel(q.getQuestao().getOpcoes()));
    return q;
  }

  public void escolhaTipoDeQuestao(ValueChangeEvent event)
  {
    this.questao.getQuestao().setTipoDeQuestao(new Integer(event.getNewValue().toString()).intValue());
  }

  public void editarQuestao(ActionEvent event)
  {
    this.questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
    this.questao.getQuestao().setEditavel(true);
    this.questao = new QuestaoRN();
  }

  public void salvarQuestao(ActionEvent event) {
    this.questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
    this.questao.salvarQuestao(this.questao);
    this.questao = new QuestaoRN();
  }

  public void adicionarQuestao(ActionEvent event) {
    this.questoes.add(novaQuestao());
  }
  
  public void excluirQuestao(ActionEvent event) {
	  this.questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
	  this.questoes.remove(questao);
  }

  public void adicionarOpcao(ActionEvent event) {
    this.questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
    this.questao.adicionarOpcao(this.questao);
    this.questao = new QuestaoRN();
  }

  public void removerOpcao(ActionEvent event) {
    this.questao = ((QuestaoRN)this.dataModelQuestoes.getRowData());
    this.questao.removerOpcao(this.questao, this.questao.getModelOpcao().getRowIndex());
  }

  public DataModel getDataModelQuestoes() {
    return this.dataModelQuestoes;
  }

  public void setDataModelQuestoes(DataModel dataModelQuestoes) {
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
  }
}