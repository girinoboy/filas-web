package br.com.regrasdenegocio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.models.Opcao;
import br.com.models.Questao;

public class QuestaoRN
{
  private DataModel modelOpcao;
  private Questao questao;
  private Map<String, String> opcoesCriadas;

  public QuestaoRN()
  {
    this.questao = new Questao("Digite a pergunta da questão", "Digite o texto de ajuda da questão", 1, false,":corpoMenuDinamico");
    this.questao.setOpcoes(new ArrayList<Opcao>());//instancia opções
    this.questao.getOpcoes().add(new Opcao());//popula com apenas um campo
//    this.opcoesCriadas = new HashMap<String, String>();
//    this.modelOpcao = new ListDataModel(this.questao.getOpcoes());
  }

  public QuestaoRN criarQuestaoModelo() {
    QuestaoRN q = new QuestaoRN();
    q.getQuestao().setOpcoes(new ArrayList<Opcao>());
    return q;
  }

  public void salvarQuestao(QuestaoRN questao) {
    questao.getQuestao().setEditavel(false);//esconde formulario de edição
    
    //não precisa desse map inutil, vai precisar depois para salvar talvez
    if (questao.getQuestao().getTipoDeQuestao() == 2) {//questão checkbox
      System.out.println("Tipo de Questão: " + questao.getQuestao().getTipoDeQuestao());
      setOpcoesCriadas(new LinkedHashMap<String, String>());
      for (Opcao c : this.questao.getOpcoes())
        questao.opcoesCriadas.put(c.getCampo(), c.getCampo()); //grava todos os campo digitados na tela nesse atributo para ser exibido depois
    }
  }

  public void adicionarOpcao(QuestaoRN questao)
  {
    questao.getQuestao().getOpcoes().add(new Opcao());
    questao.setModelOpcao(new ListDataModel(questao.getQuestao().getOpcoes()));
  }

  public void removerOpcao(QuestaoRN questao, int index) {
    questao.getQuestao().getOpcoes().remove(index);
    questao.setModelOpcao(new ListDataModel(questao.getQuestao().getOpcoes()));
  }

  public DataModel getModelOpcao() {
    return this.modelOpcao;
  }

  public void setModelOpcao(DataModel modelOpcao) {
    this.modelOpcao = modelOpcao;
  }

  public Questao getQuestao() {
    return this.questao;
  }

  public void setQuestao(Questao questao) {
    this.questao = questao;
  }

  public Map<String, String> getOpcoesCriadas() {
    return this.opcoesCriadas;
  }

  public void setOpcoesCriadas(Map<String, String> opcoesCriadas) {
    this.opcoesCriadas = opcoesCriadas;
  }

}
