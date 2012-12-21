package br.com.managedbeans;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@RequestScoped
public class DashboardBacker {
 
    public static final int DEFAULT_COLUMN_COUNT = 5;
    private int columnCount = DEFAULT_COLUMN_COUNT;
     
    private Dashboard dashboard;
    private String titulo;
 
    public DashboardBacker() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
 
        dashboard = (Dashboard) application.createComponent("org.primefaces.component.Dashboard");
        dashboard.setId("dashboard");
        
 
        DashboardModel model = new DefaultDashboardModel();
        for( int i = 0, n = getColumnCount(); i < n; i++ ) {
            DashboardColumn column = new DefaultDashboardColumn();
            
            model.addColumn(column);
        }
        dashboard.setModel(model);
 
        int items = 5;
         
        for( int i = 0, n = items; i < n; i++ ) {
            Panel panel = (Panel) application.createComponent("org.primefaces.component.Panel");
            panel.setId("measure_" + i);
            panel.setHeader("Dashboard Component " + i);
            panel.setClosable(true);
            panel.setToggleable(true);
 
            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(i%getColumnCount());
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue("Dashboard widget bits!" );
 
            panel.getChildren().add(text);
        }
    }
    
    public void adicionarioWidget(){
    	
    	FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        DashboardModel model = dashboard.getModel();
    	
    	Panel panel = (Panel) application.createComponent("org.primefaces.component.Panel");
        panel.setId("measure_" + dashboard.getChildCount()+1);
        panel.setHeader(titulo);
        panel.setClosable(true);
        panel.setToggleable(true);

        getDashboard().getChildren().add(panel);
        DashboardColumn column = model.getColumn(0);
        column.addWidget(panel.getId());
        HtmlOutputText text = new HtmlOutputText();
        text.setValue("Dashboard widget bits!" );
        
        CommandLink link = new CommandLink();
        link.setId("lk_" + dashboard.getChildCount()+1);
        
        ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		MethodExpression methodExpression = factory.createMethodExpression(elContext,"#{indexController.target}", Object.class, new Class[] {});
        
        link.setActionExpression(methodExpression);
        link.setValue("Clique para criar questões");
        UIParameter param = new UIParameter();
        param = new UIParameter();
		param.setName("idMenu");
		param.setValue(19);
        link.getChildren().add(param);
        param = new UIParameter();
		param.setName("pagina");
		param.setValue("questionario.xhtml");
        link.getChildren().add(param);
        //link.setUpdate(":corpoMenuDinamico");
        link.setUpdate("dynamic_dashboard");
        
        //panel.getChildren().add(text);
        panel.getChildren().add(link);
        
    }
 
    public Dashboard getDashboard() {
        return dashboard;
    }
 
    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
 
    public int getColumnCount() {
        return columnCount;
    }
 
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
