/**
 * 
 */
package br.com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
	private static final Integer PORT = 465;//578

	private static EmailBean emailBean = new EmailBean();

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	public EmailUtils() {
	    // o arquivo encontra-se no mesmo diretório //da aplicação //jboss_home/bin
	    File file = new File("mail.properties");  
	    Properties props = new Properties();
	    FileInputStream fis = null;
	    
	    FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivoSalvo = scontext.getRealPath("/");
		
		
	    try {
	        fis = new FileInputStream(file);  
	        //lê os dados que estão no arquivo  
	        props.load(fis);
	        fis.close();
	        
	        Properties prop = new Properties();
	        //load a properties file
    		prop.load(new FileInputStream("mail.properties"));
    		
    		//get the property value and print it out
            System.out.println(prop.getProperty("mail.user"));
	        
	        String user = props.getProperty("mail.user");
	        String from = props.getProperty("mail.from");
	        String smtp = props.getProperty("mail.smtp.host");
	        String pop3 = props.getProperty("mail.pop3.host");
	        String protocol = props.getProperty("mail.store.protocol");
	        String debug = props.getProperty("mail.debug");
	        
	    }
	    catch (IOException ex) {
	        System.out.println(ex.getMessage());
	        ex.printStackTrace();
	    }
	}

	private static Email conectaEmailSimples() throws Exception,EmailException{

		Email email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(EMAILORIGEM);
		//email.setDebug(true);

		return email;

	}

	private static HtmlEmail conectaEmailHtml() throws Exception,EmailException{

		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(PORT);
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

	public void enviaEmailHtml(EmailBean emailBean) throws Exception{
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email = conectaEmailHtml();
		email.setSubject(emailBean.getSubject());
		email.setFrom("me@apache.org", "Me");
		email.addTo(emailBean.getTo());

		// embed the image and get the content id
		URL url = new URL(emailBean.getUrlArte());
		String img = email.embed(url, "Oren logo");

		// load your HTML email template
		String htmlEmailTemplate = //"<html>The Oren logo - <img src=\"cid:"+cid+"\"></html>";
		Util.lerArquivoURL(new URL(emailBean.getUrlArquivoTemplate()));
		htmlEmailTemplate = htmlEmailTemplate.replace("${urlArte}", img);
		htmlEmailTemplate = htmlEmailTemplate.replace("${urlQuestionario}", emailBean.getUrlQuestionario());
		
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
			emailBean.setUrlArquivoTemplate("/layout/templateEmail.html");
			emailBean.setUrlArte("img/email_modelo.png");
			EmailUtils emailUtils = new EmailUtils();
			//enviaEmail(emailBean);
			emailUtils.enviaEmailHtml(emailBean);
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
