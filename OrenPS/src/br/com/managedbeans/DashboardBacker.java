package br.com.managedbeans;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.com.dao.QuestionarioDAO;
import br.com.models.Questionario;

@ManagedBean
@RequestScoped
public class DashboardBacker {

	public static final int DEFAULT_COLUMN_COUNT = 5;
	private int columnCount = DEFAULT_COLUMN_COUNT;

	private Dashboard dashboard;
	private Questionario questionario =  new Questionario();
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();

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
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			Application application = fc.getApplication();
			DashboardModel model = dashboard.getModel();
			//cria no banco antes de criar visualmente. Se der erro no banco não será criado visualmente.
			questionarioDAO.save(questionario);
			
			Panel panel = (Panel) application.createComponent("org.primefaces.component.Panel");
			panel.setId("measure_" + dashboard.getChildCount()+1);
			panel.setHeader(questionario.getTitulo());
			panel.setClosable(true);
			//panel.setToggleable(true);

			getDashboard().getChildren().add(panel);
			DashboardColumn column = model.getColumn(0);
			column.addWidget(panel.getId());

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

			panel.getChildren().add(link);
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}

	}

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
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
