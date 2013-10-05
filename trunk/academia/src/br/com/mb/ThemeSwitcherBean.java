package br.com.mb;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.hibernate.HibernateException;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

@ManagedBean
@RequestScoped
public class ThemeSwitcherBean extends GenericoMB{
          
    private Map<String, String> themes;
    
    private UsuarioDAO usuarioDAO =  new UsuarioDAO();
      
    private String theme;
    
    @ManagedProperty(value = "#{guestPreferences}")
    private GuestPreferences gp;
  
    @PostConstruct
    public void init() {
        theme = gp.getTheme();
        //TODO mudar para pegar valores do banco de dados.
        themes = new TreeMap<String, String>();
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Bootstrap", "bootstrap");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
    }
      
    public void saveTheme() throws HibernateException, Exception {
    	
    	UsuarioDTO usuario = getUserSession();
    	if(theme !=null){
    		gp.setTheme(theme);
    		usuario.setTema(theme);
    	}
    	usuarioDAO.saveTheme(theme,usuario);
    }
    
    public void setGp(GuestPreferences gp) {
        this.gp = gp;
    }
      
    public Map<String, String> getThemes() {
        return themes;
    }
  
    public String getTheme() {
        return theme;
    }
  
    public void setTheme(String theme) {
        this.theme = theme;
    }
}  
