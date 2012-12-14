package br.com.managedbeans;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@RequestScoped
public class DashboardBacker {
 
    public static final int DEFAULT_COLUMN_COUNT = 3;
    private int columnCount = DEFAULT_COLUMN_COUNT;
     
    private Dashboard dashboard;
 
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
}
