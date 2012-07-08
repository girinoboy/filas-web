/** ManagedBean que comunica com a visão (JSF) e usa o JavaBean Contato */

package br.com.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "agendaContatoBean")
@SessionScoped
public class AgendaContatoBean {

	@ManagedProperty(value = "#{contato}")
	private Contato			contato;
	private List<Contato>	contatos;

	public AgendaContatoBean() {
		this.setContatos(new ArrayList<Contato>());
		this.setContato(new Contato());
	}

	public void incluirContato(ActionEvent event) {
		this.getContatos().add(this.getContato());
		this.setContato(new Contato());
	}

	public List<Contato> getContatos() {
		return this.contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContato() {
		return this.contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
