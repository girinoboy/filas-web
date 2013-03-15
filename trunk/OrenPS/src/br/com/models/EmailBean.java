/**
 * 
 */
package br.com.models;

/**
 * @author marcleonio.medeiros
 *
 */

public class EmailBean {
	
	private String subject;
	private String msg;
	private String to;
	private String urlArte;
	private String urlArquivoTemplate;
	private String urlQuestionario;

	/**
	 * 
	 */
	public EmailBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the urlArte
	 */
	public String getUrlArte() {
		return urlArte;
	}

	/**
	 * @param urlArte the urlArte to set
	 */
	public void setUrlArte(String urlArte) {
		this.urlArte = urlArte;
	}

	/**
	 * @return the urlArquivoTemplate
	 */
	public String getUrlArquivoTemplate() {
		return urlArquivoTemplate;
	}

	/**
	 * @param urlArquivoTemplate the urlArquivoTemplate to set
	 */
	public void setUrlArquivoTemplate(String urlArquivoTemplate) {
		this.urlArquivoTemplate = urlArquivoTemplate;
	}

	/**
	 * @return the urlQuestionario
	 */
	public String getUrlQuestionario() {
		return urlQuestionario;
	}

	/**
	 * @param urlQuestionario the urlQuestionario to set
	 */
	public void setUrlQuestionario(String urlQuestionario) {
		this.urlQuestionario = urlQuestionario;
	}


}
