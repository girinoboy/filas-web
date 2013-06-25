/**
 * 
 */
package br.com.mb;

/**
 * @author Marcleônio
 *
 */
import java.io.Serializable;  
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
//import org.primefaces.model.chart.BarChartSeries;

import br.com.dao.ChartDAO;
import br.com.dto.NewView;


@ManagedBean(name="chartBean")
@RequestScoped
public class ChartMB implements Serializable {  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartesianChartModel linearModel;  

	private CartesianChartModel categoryModel;  

	private PieChartModel livePieModel;

	private CartesianChartModel combinedModel; 
	
	private Double previsao = 22D;

	public ChartMB(){  
		createLinearModel();  
		createCategoryModel(); 
		createLivePieModel(); 
		createCombinedModel();  
	}  

	public CartesianChartModel getLinearModel() {  
		return linearModel;  
	}  

	public CartesianChartModel getCategoryModel() {  
		return categoryModel;  
	}  

	public void atualizaGrafico() {
		createLinearModel(); 
	}

	private void createLinearModel() {  

		linearModel = new CartesianChartModel();  
		ChartDAO chartDAO = new ChartDAO();
		String tabela = "new_view";
		String colunaX = "dia";
		String colunaY = "venda";
		//Double previsao = null;

		List<NewView> a = null;
		try {
			a = chartDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LineChartSeries series1 = new LineChartSeries();  
		series1.setLabel("Real");  
		series1.setMarkerStyle("diamond");
		for (NewView newView : a) {
			series1.set(newView.getDia().intValue(), newView.getVenda());
		}

		LineChartSeries series2 = new LineChartSeries();

		series2.setLabel("Previsão");  
		
//		for (NewView newView : a) {
//			series2.set(newView.getCusto().intValue(), newView.getQuantidade());
//		}
		series2.set(previsao.intValue(), chartDAO.minerar(tabela, colunaX, colunaY, previsao));
		
		linearModel.addSeries(series1);  
		linearModel.addSeries(series2);  
	}  

	private void createCategoryModel() {  
		categoryModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();  
		boys.setLabel("Produto A");  

		boys.set("2004", 120);  
		boys.set("2005", 100);  
		boys.set("2006", 44);  
		boys.set("2007", 150);  
		boys.set("2008", 25);  

		ChartSeries girls = new ChartSeries();  
		girls.setLabel("Produto B");  

		girls.set("2004", 52);  
		girls.set("2005", 60);  
		girls.set("2006", 110);  
		girls.set("2007", 135);  
		girls.set("2008", 120);  

		categoryModel.addSeries(boys);  
		categoryModel.addSeries(girls);  
	}

	public PieChartModel getLivePieModel() {  
		int random1 = (int)(Math.random() * 1000);  
		int random2 = (int)(Math.random() * 1000);  

		livePieModel.getData().put("Candidate 1", random1);  
		livePieModel.getData().put("Candidate 2", random2);  

		return livePieModel;  
	}  

	private void createLivePieModel() {  
		livePieModel = new PieChartModel();  

		livePieModel.set("Candidate 1", 540);  
		livePieModel.set("Candidate 2", 325);  
	}  

	private void createCombinedModel() {  
		combinedModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();   
		boys.setLabel("Boys");  

		boys.set("2004", 120);  
		boys.set("2005", 100);  
		boys.set("2006", 44);  
		boys.set("2007", 150);  
		boys.set("2008", 25);  

		LineChartSeries girls = new LineChartSeries();  
		girls.setLabel("Girls");  

		girls.set("2004", 52);  
		girls.set("2005", 60);  
		girls.set("2006", 110);  
		girls.set("2007", 135);  
		girls.set("2008", 120);  

		combinedModel.addSeries(boys);  
		combinedModel.addSeries(girls);  
	}  

	/**
	 * @param linearModel the linearModel to set
	 */
	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}

	/**
	 * @param categoryModel the categoryModel to set
	 */
	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	/**
	 * @param livePieModel the livePieModel to set
	 */
	public void setLivePieModel(PieChartModel livePieModel) {
		this.livePieModel = livePieModel;
	}

	/**
	 * @return the combinedModel
	 */
	public CartesianChartModel getCombinedModel() {
		return combinedModel;
	}

	/**
	 * @param combinedModel the combinedModel to set
	 */
	public void setCombinedModel(CartesianChartModel combinedModel) {
		this.combinedModel = combinedModel;
	}

	/**
	 * @return the previsao
	 */
	public Double getPrevisao() {
		return previsao;
	}

	/**
	 * @param previsao the previsao to set
	 */
	public void setPrevisao(Double previsao) {
		this.previsao = previsao;
	}  
}  

