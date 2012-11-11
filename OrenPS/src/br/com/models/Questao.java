package br.com.models;

import java.io.Serializable;
import java.util.List;

public class Questao
  implements Serializable
{
  private static final long serialVersionUID = -777752470916891894L;
  private String pergunta;
  private String textoDeAjuda;
  private List<Opcao> opcoes;
  private int tipoDeQuestao;
  private boolean editavel;

  public Questao()
  {
  }

  public Questao(String pergunta, String textoDeAjuda, int tipoDeQuestao, boolean editavel)
  {
    this.editavel = editavel;
    this.pergunta = pergunta;
    this.textoDeAjuda = textoDeAjuda;
    this.tipoDeQuestao = tipoDeQuestao;
  }

  public List<Opcao> getOpcoes()
  {
    return this.opcoes;
  }

  public void setOpcoes(List<Opcao> opcoes) {
    this.opcoes = opcoes;
  }

  public boolean isEditavel() {
    return this.editavel;
  }

  public void setEditavel(boolean editavel) {
    this.editavel = editavel;
  }

  public String getPergunta() {
    return this.pergunta;
  }

  public void setPergunta(String pergunta) {
    this.pergunta = pergunta;
  }

  public String getTextoDeAjuda() {
    return this.textoDeAjuda;
  }

  public void setTextoDeAjuda(String textoDeAjuda) {
    this.textoDeAjuda = textoDeAjuda;
  }

  public int getTipoDeQuestao() {
    return this.tipoDeQuestao;
  }

  public void setTipoDeQuestao(int tipoDeQuestao) {
    this.tipoDeQuestao = tipoDeQuestao;
  }
}