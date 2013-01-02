package br.com.regrasdenegocio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.dao.OpcaoDAO;
import br.com.dao.QuestaoDAO;
import br.com.models.Opcao;
import br.com.models.Questao;
import br.com.models.Questionario;

public class QuestaoRN
{
	private DataModel modelOpcao;
	private Questao questao;
	private Map<String, String> opcoesCriadas;
	private QuestaoDAO questaoDAO = new QuestaoDAO();
	private OpcaoDAO opcaoDAO = new OpcaoDAO();

	public QuestaoRN(){}

	public QuestaoRN criarQuestaoModelo(Questionario questionario) throws Exception {
		QuestaoRN q = new QuestaoRN();
		try {
			this.questao = new Questao("Digite a pergunta da questão", "Digite o texto de ajuda da questão", 1, false,":corpoMenuDinamico");
			this.questao.setOpcoes(new ArrayList<Opcao>());//instancia opções
			this.questao.getOpcoes().add(new Opcao(questao));//popula com apenas um campo
			questao.setQuestionario(questionario);
			questao=(questaoDAO.save(questao));
			q.setQuestao(questao);
			//q.getQuestao().setOpcoes(new ArrayList<Opcao>());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return q;
	}

	public QuestaoRN salvarQuestao(QuestaoRN questao) {
		questao.getQuestao().setEditavel(false);//esconde formulario de edição

		//não precisa desse map inutil, vai precisar depois para salvar talvez
		/*
		if (questao.getQuestao().getTipoDeQuestao() == 2) {//questão checkbox
			System.out.println("Tipo de Questão: " + questao.getQuestao().getTipoDeQuestao());
			setOpcoesCriadas(new LinkedHashMap<String, String>());
			for (Opcao c : this.questao.getOpcoes())
				questao.opcoesCriadas.put(c.getCampo(), c.getCampo()); //grava todos os campo digitados na tela nesse atributo para ser exibido depois
		}*/
		
		return questao;
	}

	public void adicionarOpcao(QuestaoRN questao)
	{
		questao.getQuestao().getOpcoes().add(new Opcao(questao.getQuestao()));
		questao.setModelOpcao(new ListDataModel(questao.getQuestao().getOpcoes()));
	}

	public void removerOpcao(QuestaoRN questao, int index) throws Exception {
		opcaoDAO.delete(questao.getQuestao().getOpcoes().get(index));
		questao.getQuestao().getOpcoes().remove(index);
		questao.setModelOpcao(new ListDataModel(questao.getQuestao().getOpcoes()));
	}

	public DataModel getModelOpcao() {
		return modelOpcao;
	}

	public void setModelOpcao(DataModel modelOpcao) {
		this.modelOpcao = modelOpcao;
	}

	public Questao getQuestao() {
		if(questao==null){
			questao = new Questao();
		}
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Map<String, String> getOpcoesCriadas() {
		return opcoesCriadas;
	}

	public void setOpcoesCriadas(Map<String, String> opcoesCriadas) {
		this.opcoesCriadas = opcoesCriadas;
	}

}
