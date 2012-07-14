package br.com.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.tables.Filial;

@ManagedBean(name = "filialBean")
@RequestScoped
public class FilialBean {

	private Filial filial;
	
	public FilialBean(){
		setFilial(new Filial());
	}

	public List<Filial> listarFiliais() {
		List<Filial> filial = new ArrayList<Filial>();
		return filial;
	}

	public String salvar() {

		return "";
	}
	
	

	public Filial getFilial() {
		if(filial == null){
			filial = new Filial();
		}
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
