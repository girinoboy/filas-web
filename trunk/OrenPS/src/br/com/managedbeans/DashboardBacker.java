package br.com.managedbeans;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.com.dao.QuestionarioDAO;
import br.com.models.Questionario;

@ManagedBean
@RequestScoped
public class DashboardBacker {

	public static final int DEFAULT_COLUMN_COUNT = 4;
	private int columnCount = DEFAULT_COLUMN_COUNT;

	private Dashboard dashboard;
	private Questionario questionario =  new Questionario();
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();

	public DashboardBacker() {
		try{
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

			List<Questionario> listQuestionario = questionarioDAO.listOrdenada();
			int i = 0;
			for (Questionario questionario : listQuestionario) {
				Panel panel = (Panel) application.createComponent("org.primefaces.component.Panel");
				panel.setId("p_"+questionario.getId().toString());
				panel.setHeader(questionario.getTitulo());
				panel.setClosable(true);
				//panel.setToggleable(true);

				getDashboard().getChildren().add(panel);
				DashboardColumn column = model.getColumn(i%getColumnCount());
				column.addWidget(panel.getId());
				
//				column.reorderWidget(0, panel.getId());
				DashboardColumn oldColumn=model.getColumn(i%getColumnCount());
				DashboardColumn newColumn= model.getColumn(questionario.getDashboardColumn());
				//model.transferWidget(oldColumn, newColumn, widgetId, itemIndex);
				model.transferWidget(oldColumn, newColumn, panel.getId(), questionario.getItemIndex());
				//model.transferWidget(arg0, arg1, arg2, arg3);
				
				panel.getChildren().add(criaLink(i));

				i++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void adicionarioWidget(){
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			Application application = fc.getApplication();
			DashboardModel model = dashboard.getModel();
			//cria no banco antes de criar visualmente. Se der erro no banco não será criado visualmente.
			questionario.setDashboardColumn(0);
			questionario.setItemIndex(0);
			questionario = questionarioDAO.save(questionario);

			Panel panel = (Panel) application.createComponent("org.primefaces.component.Panel");
			panel.setId("p_"+questionario.getId().toString());
			panel.setHeader(questionario.getTitulo());
			panel.setClosable(true);
			//panel.setToggleable(true);

			getDashboard().getChildren().add(panel);
			DashboardColumn column = model.getColumn(0);
			column.addWidget(panel.getId());

			panel.getChildren().add(criaLink(dashboard.getChildCount()+1));
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}

	}

	public void handleReorder(DashboardReorderEvent event) {  
		try{
			FacesMessage message = new FacesMessage();  
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Reordered: " + event.getWidgetId());
			message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex()); 

			questionario.setId(Long.valueOf(event.getWidgetId().substring(2,event.getWidgetId().length())));
			questionario.setDashboardColumn(event.getColumnIndex());
			questionario.setItemIndex(event.getItemIndex());
			
			
			questionarioDAO.salvaColunaIndex(questionario);

			addMessage(message);
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}
	
	private UIComponent criaLink(int i) {
		CommandLink link = new CommandLink();
		link.setId("lk_" + i);

		ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		MethodExpression methodExpression = factory.createMethodExpression(elContext,"#{indexController.target}", Object.class, new Class[] {});

		link.setActionExpression(methodExpression);
		link.setValue("Clique para editar questões");
		link.setProcess("@none");
		
		UIParameter param = new UIParameter();
		param = new UIParameter();
		param.setName("idMenu");
		param.setValue("");
		link.getChildren().add(param);

		param = new UIParameter();
		param.setName("pagina");
		param.setValue("questionario.xhtml");
		link.getChildren().add(param);
		link.setUpdate(":corpoMenuDinamico");
		//link.setUpdate("dynamic_dashboard");
		return link;
	}

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}

	private void addMessage(FacesMessage message) {  
		FacesContext.getCurrentInstance().addMessage(null, message);  
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
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
}
