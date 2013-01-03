package br.com.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcleonio
 */
@Entity
@Table(name = "questoes")
public class Questao  implements Serializable {

	private static final long serialVersionUID = -777752470916891894L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "questionario_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = false)
	private Questionario questionario;
	private String pergunta;
	private String textoDeAjuda;
	@OneToMany(mappedBy = "questao", targetEntity = Opcao.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="opcoes_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = true)
	private List<Opcao> opcoes;
	private Integer tipoDeQuestao;
	private Boolean editavel;
	private String atualiza;

	public Questao() {}

	public Questao(String pergunta, String textoDeAjuda, int tipoDeQuestao, boolean editavel,String atualiza){
		this.editavel = editavel;
		this.pergunta = pergunta;
		this.textoDeAjuda = textoDeAjuda;
		this.tipoDeQuestao = tipoDeQuestao;
		this.atualiza = atualiza;
	}

	public List<Opcao> getOpcoes() {
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

	public String getAtualiza() {
		return atualiza;
	}

	public void setAtualiza(String atualiza) {
		this.atualiza = atualiza;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	/**
	 * @return the editavel
	 */
	public Boolean getEditavel() {
		return editavel;
	}

	/**
	 * @param editavel the editavel to set
	 */
	public void setEditavel(Boolean editavel) {
		this.editavel = editavel;
	}

	/**
	 * @param tipoDeQuestao the tipoDeQuestao to set
	 */
	public void setTipoDeQuestao(Integer tipoDeQuestao) {
		this.tipoDeQuestao = tipoDeQuestao;
	}
}