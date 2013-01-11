/**
 * 
 */
package br.com.utility;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.managedbeans.IndexController;
import br.com.models.EmailBean;

/**
 * @author marcleonio.medeiros
 *
 */
public class EmailUtils {

	private static final String HOSTNAME = "smtp.gmail.com";//"smtp.googlemail.com"
	private static final String USERNAME = "oren.software@gmail.com";
	private static final String PASSWORD = "OrenSoft";
	private static final String EMAILORIGEM = "user@gmail.com";
	private static final Integer PORTA = 465;//578

	private static EmailBean emailBean = new EmailBean();

	/**
	 * 
	 */
	public EmailUtils() {
		// TODO Auto-generated constructor stub
	}

	private static Email conectaEmailSimples() throws Exception,EmailException{

		Email email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(PORTA);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(EMAILORIGEM);
		//email.setDebug(true);

		return email;

	}

	private static HtmlEmail conectaEmailHtml() throws Exception,EmailException{

		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(PORTA);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(EMAILORIGEM);
		//email.setDebug(true);
		
		return email;

	}


	public static void enviaEmailSimples(EmailBean emailBean) throws Exception {
		Email email = new SimpleEmail();
		email = conectaEmailSimples();
		email.setSubject(emailBean.getSubject());
		email.setMsg(emailBean.getMsg());
		email.addTo(emailBean.getTo());
		String resposta = email.send();
		System.out.println(resposta);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + emailBean.getTo(), "Informação"));
	}

	public static void enviaEmailHtml(EmailBean emailBean) throws Exception{
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email = conectaEmailHtml();
		email.setSubject(emailBean.getSubject());
		email.setFrom("me@apache.org", "Me");
		email.addTo(emailBean.getTo());

		// embed the image and get the content id
		URL url = new URL("http://admargarida.com.br/wp/wp-content/uploads/2013/01/arvore-frutifera.jpg");
		String cid = email.embed(url, "Oren logo");

		// load your HTML email template
		String htmlEmailTemplate = "<html>The Oren logo - <img src=\"cid:"+cid+"\"></html>";

		// set the html message
		email.setHtmlMsg(htmlEmailTemplate);

		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		// send the email
		//email.send();

		String resposta = email.send();
		System.out.println(resposta);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + emailBean.getTo(), "Informação"));


	}

	public static void main(String []args){
		try {
			emailBean.setSubject("TestMail");
			emailBean.setMsg("This is a test mail ... :-)");
			emailBean.setTo("girinoboy@gmail.com");
			//enviaEmail(emailBean);
			enviaEmailHtml(emailBean);
		} catch (EmailException e) {
			Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Occoreu um erro ao enviar a mensagem.", "Erro"));
			Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, e);

			e.printStackTrace();
		}
	}

}
