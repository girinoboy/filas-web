/**
 * 
 */
package br.com.managedbeans;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
import java.util.UUID;  
  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletContext;  
  
import org.primefaces.model.LazyDataModel;

import br.com.dao.UsuarioQuestionarioDAO;
import br.com.models.UsuarioQuestionario;
/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class EmailControleManagedBean {

	
	
	private LazyDataModel<UsuarioQuestionario> lazyModel;  
	  
    private UsuarioQuestionario selectedCar;  
  
    private List<UsuarioQuestionario> usuarioQuestionario;

	private static String[] colors;

	private static String[] manufacturers;  
	
	private UsuarioQuestionarioDAO usuarioQuestionarioDAO = new UsuarioQuestionarioDAO();
  
    static {  
        colors = new String[10];  
        colors[0] = "Black";  
        colors[1] = "White";  
        colors[2] = "Green";  
        colors[3] = "Red";  
        colors[4] = "Blue";  
        colors[5] = "Orange";  
        colors[6] = "Silver";  
        colors[7] = "Yellow";  
        colors[8] = "Brown";  
        colors[9] = "Maroon";  
          
        manufacturers = new String[10];  
        manufacturers[0] = "Mercedes";  
        manufacturers[1] = "BMW";  
        manufacturers[2] = "Volvo";  
        manufacturers[3] = "Audi";  
        manufacturers[4] = "Renault";  
        manufacturers[5] = "Opel";  
        manufacturers[6] = "Volkswagen";  
        manufacturers[7] = "Chrysler";  
        manufacturers[8] = "Ferrari";  
        manufacturers[9] = "Ford";  
    }  
  
    /**
	 * 
	 */
	public EmailControleManagedBean() {
		 //populateRandomCars(usuarioQuestionario, 50);  
	        
	        try {
	        	usuarioQuestionario = usuarioQuestionarioDAO.list();
	        	lazyModel = new LazyUsuarioQuestionarioDataModel(usuarioQuestionario);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	} 
  
    public UsuarioQuestionario getSelectedCar() {  
        return selectedCar;  
    }  
  
    public void setSelectedCar(UsuarioQuestionario selectedCar) {  
        this.selectedCar = selectedCar;  
    }  
      
    public LazyDataModel<UsuarioQuestionario> getLazyModel() {  
        return lazyModel;  
    }  
  
    private void populateRandomCars(List<UsuarioQuestionario> list, int size) {  
        for(int i = 0 ; i < size ; i++) {  
            list.add(new UsuarioQuestionario(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));  
        }  
    }  
  
    private String getRandomModel() {
		
		return String.valueOf((Math.random() * 100))  ;
	}

	private String getRandomColor() {  
        return colors[(int) (Math.random() * 10)];  
    }  
  
    private String getRandomManufacturer() {  
        return manufacturers[(int) (Math.random() * 10)];  
    }  
  
    private int getRandomYear() {  
        return (int) (Math.random() * 50 + 1960);  
    }
	
    public void onRowSelect(){
    	
    }
}
