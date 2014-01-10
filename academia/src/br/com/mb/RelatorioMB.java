/**
 * 
 */
package br.com.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;

import org.hibernate.HibernateException;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import br.com.dao.FrequenciaDAO;
import br.com.dao.PagamentoDAO;

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
	private CartesianChartModel lucroAno;

	private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
	private PagamentoDAO pagamentoDAO = new PagamentoDAO();
	
	private Map<String, Integer> listAno,listMes;
	private Integer mes,ano;

	public RelatorioMB() throws HibernateException, Exception {  
		createCategoryModel();  
		createLinearModel();  
		frequenciaMes();
		lucroAno();
		
		listMes = new TreeMap<String, Integer>();
		listMes.put("Janeiro", 0);
		listMes.put("Fevereiro", 1);
		listMes.put("Março", 2);
		listMes.put("Abril", 3);
		listMes.put("Maio", 4);
		listMes.put("Junho", 5);
		listMes.put("Julho", 6);
		listMes.put("Agosto", 7);
		listMes.put("Setembro", 8);
		listMes.put("Outubro", 9);
		listMes.put("Novembro", 10);
		listMes.put("Dezembro", 11);
		
		 @SuppressWarnings("rawtypes")
		List pagamentoDTO = pagamentoDAO.consultaHQL("select ano from PagamentoDTO group by ano");
		 
		 for (Object p : pagamentoDTO) {
			 listAno = new TreeMap<String, Integer>();
			 System.out.println(p);
			 listAno.put(p.toString(), Integer.valueOf(p.toString()));
		}
		 
		
	}  
	//select * from usuario where frequencia.data = today
	public CartesianChartModel getCategoryModel() {  
		return categoryModel;  
	}  

	public CartesianChartModel getLinearModel() {  
		return linearModel;  
	}  
	@SuppressWarnings("rawtypes")
	public void lucroAno(){
		lucroAno = new CartesianChartModel(); 
		
		ChartSeries chartSeries = new ChartSeries();  
		chartSeries.setLabel("Margem de Lucro");
		
		//popula o mes todos
		for (int i = 1; i <= 12; i++) {
			chartSeries.set(i,0);
		}
		try {
			if(ano==null){
				ano = new GregorianCalendar().get(Calendar.YEAR);
			}
			
			List l = pagamentoDAO.mediaLucroAno(ano);
			
			
			Iterator it = l.iterator();
			while(it.hasNext())  
			{  
				Object[] c = (Object[]) it.next();  
				System.out.println(c[0]);
				System.out.println(c[1]);

				//mes-avg(valor)
				chartSeries.set((int)c[0]+1,(Double)c[1]); 

			}
			
			lucroAno.addSeries(chartSeries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void frequenciaMes(){
		frequenciaMes = new CartesianChartModel(); 

		ChartSeries boys = new ChartSeries();  
		boys.setLabel("Masculino");

		ChartSeries girls = new ChartSeries();  
		girls.setLabel("Feminino");

		try {
			if(ano==null){
				ano = new GregorianCalendar().get(Calendar.YEAR);
			}
			if(mes==null){
				mes = new GregorianCalendar().get(Calendar.MONTH);
			}

			//frequenciaDAO.consultaHQL("select count(*), date from FrequenciaDTO");
			Calendar data = new GregorianCalendar(ano, mes, Calendar.DAY_OF_MONTH);
			//popula o mes todos
			for (int i = 1; i <= data.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
				boys.set(i,0);
				girls.set(i,0);
			}
			
			Iterator it = null;
			List m = frequenciaDAO.frequenciaMesPorSexo("1",mes,ano);
			it = m.iterator();
			while(it.hasNext())
			{  
				Object[] c = (Object[]) it.next();  
				System.out.println(c[0]);
				System.out.println(c[1]);

				data.setTime((Date) c[0]);

				boys.set(data.get(Calendar.DAY_OF_MONTH),(int)c[1]); 
/*
				for (int i = 0; i < c.length; i++) {
					System.out.println(c[i]);
				}*/

			}

			List f = frequenciaDAO.frequenciaMesPorSexo("0",mes,ano);
			it = f.iterator();
			while(it.hasNext())
			{  
				Object[] c = (Object[]) it.next();  
				System.out.println(c[0]);
				System.out.println(c[1]);

				data.setTime((Date) c[0]);

				girls.set(data.get(Calendar.DAY_OF_MONTH),(int)c[1]); 
/*
				for (int i = 0; i < c.length; i++) {
					System.out.println(c[i]);
				}*/
			}
			

			frequenciaMes.addSeries(boys);  
			frequenciaMes.addSeries(girls); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void createCategoryModel() {  
		categoryModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();  
		boys.setLabel("Masculino");

		for (int i = 1; i < 31; i++) {
			boys.set(i, (int)(Math.random() * 150));
		}
		/*boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  */

		ChartSeries girls = new ChartSeries();  
		girls.setLabel("Feminino");

		for (int i = 1; i < 31; i++) {
			girls.set(i, (int)(Math.random() * 150));
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
	public CartesianChartModel getLucroAno() {
		return lucroAno;
	}
	public void setLucroAno(CartesianChartModel lucroAno) {
		this.lucroAno = lucroAno;
	}
	public Map<String, Integer> getListAno() {
		return listAno;
	}
	public void setListAno(Map<String, Integer> listAno) {
		this.listAno = listAno;
	}
	public Map<String, Integer> getListMes() {
		return listMes;
	}
	public void setListMes(Map<String, Integer> listMes) {
		this.listMes = listMes;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}  
}  