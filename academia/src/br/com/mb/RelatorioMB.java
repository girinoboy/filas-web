/**
 * 
 */
package br.com.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;

import org.hibernate.HibernateException;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import br.com.dao.UsuarioDAO;
import br.com.dto.FrequenciaDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class RelatorioMB implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel; 
    
    private CartesianChartModel frequenciaMes; 
    UsuarioDAO usuarioDAO = new UsuarioDAO();
  
    public RelatorioMB() {  
        createCategoryModel();  
        createLinearModel();  
      //  frequenciaMes();
    }  
  //select * from usuario where frequencia.data = today
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
    private void frequenciaMes(){
    	frequenciaMes = new CartesianChartModel(); 
    	
    	ChartSeries boys = new ChartSeries();  
        boys.setLabel("Masculino");
        
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Feminino");
        
        try {
			Map<String, Object> filtrosConsulta = new HashMap<String, Object>();
			filtrosConsulta.put("dataEntrada", toMounthOnly(new Date()));
			
			List<UsuarioDTO> a = usuarioDAO.listCriterio("FrequenciaDTO", filtrosConsulta, 1);//(null"from NF where month(data) = :mes and year(data) = :ano");
			
			for (UsuarioDTO usuarioDTO : a) {
				
				
				if(usuarioDTO.getSexo().equals("1")){
					
				 boys.set("2004", 120); 
				}else{
				 girls.set("2004", 52);
				}
			}
			
			frequenciaMes.addSeries(boys);  
			frequenciaMes.addSeries(girls); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    public Date toMounthOnly(Date date){
		// ignora informação de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		calendar.clear(Calendar.DAY_OF_MONTH);
		calendar.clear(Calendar.YEAR);
		
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	} 
    
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Masculino");
  
        for (int i = 1; i < 31; i++) {
        	 boys.set(i, (int)(Math.random() * 200));
		}
        /*boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  */
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Feminino");
  
        for (int i = 1; i < 31; i++) {
        	girls.set(i, (int)(Math.random() * 200));
		}
        /*girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  */
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
    }  
  
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Series 1");  
  
        series1.set(1, 2);  
        series1.set(2, 1);  
        series1.set(3, 3);  
        series1.set(4, 6);  
        series1.set(5, 8);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Series 2");  
        series2.setMarkerStyle("diamond");  
  
        series2.set(1, 6);  
        series2.set(2, 3);  
        series2.set(3, 2);  
        series2.set(4, 7);  
        series2.set(5, 9);  
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
    }
	public CartesianChartModel getFrequenciaMes() {
		return frequenciaMes;
	}
	public void setFrequenciaMes(CartesianChartModel frequenciaMes) {
		this.frequenciaMes = frequenciaMes;
	}
	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}  
}  