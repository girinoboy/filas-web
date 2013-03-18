package br.com.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.models.Usuario;
import br.com.utility.UsuarioConverter;

@ManagedBean
public class AutoCompleteBean {

	private String txt1;
	
	private String txt2;
	
	private String txt3;
	
	private String txt4;
	
	private String txt5;
    
    private String txt6;
    
    private String txt7;

	private Usuario selectedPlayer1;
    
    private Usuario selectedPlayer2;

	private List<Usuario> players;

    private List<Usuario> selectedPlayers;
    
    private List<String> selectedTexts;
	
	public AutoCompleteBean() {
		players = UsuarioConverter.usuarioBD;
        selectedTexts = new ArrayList<String>();
	}

    public Usuario getSelectedPlayer1() {
        return selectedPlayer1;
    }

    public void setSelectedPlayer1(Usuario selectedPlayer1) {
        this.selectedPlayer1 = selectedPlayer1;
    }

    public Usuario getSelectedPlayer2() {
        return selectedPlayer2;
    }

    public void setSelectedPlayer2(Usuario selectedPlayer2) {
        this.selectedPlayer2 = selectedPlayer2;
    }

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();
		
		for(int i = 0; i < 10; i++) {
			results.add(query + i);
		}
		
		return results;
	}
	
	public List<Usuario> completePlayer(String query) {
		List<Usuario> suggestions = new ArrayList<Usuario>();
		
		for(Usuario p : players) {
			if(p.getNome().toLowerCase().startsWith(query.toLowerCase()))
				suggestions.add(p);
		}
		
		return suggestions;
	}
    
    public List<String> completeArea(String query) {
		List<String> results = new ArrayList<String>();
		
		if(query.equals("PrimeFaces")) {
            results.add("PrimeFaces Rocks!!!");
            results.add("PrimeFaces has 100+ components.");
            results.add("PrimeFaces is lightweight.");
            results.add("PrimeFaces is easy to use.");
            results.add("PrimeFaces is developed with passion!");
        }
        else {
            for(int i = 0; i < 10; i++) {
            	results.add(query + i);
            }
        }
		
		return results;
	}
	
	public void handleSelect(SelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected:" + event.getObject().toString(), null);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
    
    public void handleUnselect(UnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

    public List<Usuario> getPlayers() {
        return players;
    }

    public List<Usuario> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(List<Usuario> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public String getTxt2() {
		return txt2;
	}

	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}

	public String getTxt3() {
		return txt3;
	}

	public void setTxt3(String txt3) {
		this.txt3 = txt3;
	}
	
	public String getTxt4() {
		return txt4;
	}

	public void setTxt4(String txt4) {
		this.txt4 = txt4;
	}

	public String getTxt5() {
		return txt5;
	}

	public void setTxt5(String txt5) {
		this.txt5 = txt5;
	}

    public String getTxt6() {
        return txt6;
    }

    public void setTxt6(String txt6) {
        this.txt6 = txt6;
    }

    public String getTxt7() {
        return txt7;
    }

    public void setTxt7(String txt7) {
        this.txt7 = txt7;
    }

    public List<String> getSelectedTexts() {
        return selectedTexts;
    }
    public void setSelectedTexts(List<String> selectedTexts) {
        this.selectedTexts = selectedTexts;
    }
}


