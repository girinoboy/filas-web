package br.com.managedbeans;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.primefaces.component.behavior.ajax.AjaxBehavior;
import org.primefaces.component.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.com.dao.MenuDAO;
import br.com.dao.QuestionarioDAO;
import br.com.models.Menu;
import br.com.models.Questionario;

@ManagedBean
@RequestScoped
public class DashboardBacker {

	public static final int DEFAULT_COLUMN_COUNT = 4;
	private int columnCount = DEFAULT_COLUMN_COUNT;

	private Dashboard dashboard;
	private Questionario questionario =  new Questionario();
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	private MenuDAO menuDAO = new MenuDAO();

	public DashboardBacker() {
		try{
			dashboard = new Dashboard();
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
				Panel panel = criaPanel(questionario);

				getDashboard().getChildren().add(panel);
				DashboardColumn column = model.getColumn(i%getColumnCount());
				column.addWidget(panel.getId());

				//column.reorderWidget(0, panel.getId());
				DashboardColumn oldColumn=model.getColumn(i%getColumnCount());
				DashboardColumn newColumn= model.getColumn(questionario.getDashboardColumn());
				//model.transferWidget(oldColumn, newColumn, widgetId, itemIndex);
				model.transferWidget(oldColumn, newColumn, panel.getId(), questionario.getItemIndex());
				//model.transferWidget(arg0, arg1, arg2, arg3);

				panel.getChildren().add(criaLink(questionario,i));

				i++;
			}

		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	private Panel criaPanel(Questionario questionario) throws Exception {

		FacesContext ctx = FacesContext.getCurrentInstance();
		Panel panel = new Panel();
		try{
			panel.setId("p_"+questionario.getId().toString()+"_m_"+questionario.getMenu().getId());
			panel.setHeader(questionario.getTitulo());
			panel.setClosable(true);
			//panel.setToggleable(true);

			//para Behavior <p:ajax even='close'>
			AjaxBehavior ajaxBehavior = new AjaxBehavior();
			ExpressionFactory ef = ctx.getApplication().getExpressionFactory();
			String expression ="#{dashboardBacker.handleClose}";
			Class<?> expectedReturnType = void.class;
			Class<?>[] expectedParamTypes =  new Class[] {Object.class};
			MethodExpression me = ef.createMethodExpression(ctx.getELContext(),
					expression,//Sua ELExpression #{dashboardBacker.handleClose}
					expectedReturnType, //nesse caso null
					expectedParamTypes); //se receber paremetro colocar new Class[]{Object.class} se não apenas new Class[]
			ajaxBehavior.addAjaxBehaviorListener( new AjaxBehaviorListenerImpl( me ) );
			//ajaxBehavior.setListener(me);
			ajaxBehavior.setUpdate("growl");
			panel.addClientBehavior( "close", ajaxBehavior);
		}catch(Exception e){
			throw e;
		}
		return panel;
	}

	public void adicionarioWidget(){
		try{
			DashboardModel model = dashboard.getModel();

			//cria no banco antes de criar visualmente. Se der erro no banco não será criado visualmente.
			questionario.setDashboardColumn(0);
			questionario.setItemIndex(0);
			questionario = questionarioDAO.save(questionario);
			
			Menu menu = new Menu();
			menu.setDescricao(questionario.getTitulo());
			menu.setPagina("responderQuestionario.xhtml");
			menu.setQuestionario(questionario);
			Menu sub = new Menu();
			sub.setId(8);
			menu.setSub(sub);
			menuDAO.save(menu);//cria link no meu dinamico

			Panel panel = criaPanel(questionario);

			getDashboard().getChildren().add(panel);
			DashboardColumn column = model.getColumn(0);
			column.addWidget(panel.getId());

			panel.getChildren().add(criaLink(questionario,dashboard.getChildCount()+1));
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

			questionario.setId(Integer.valueOf(event.getWidgetId().substring(2,event.getWidgetId().length())));
			questionario.setDashboardColumn(event.getColumnIndex());
			questionario.setItemIndex(event.getItemIndex());


			questionarioDAO.salvaColunaIndex(questionario);

			addMessage(message);
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public void handleClose(CloseEvent event) {
		try {
			
			questionario.setId(Integer.valueOf(event.getComponent().getId().substring(2,event.getComponent().getId().indexOf("_m_"))));
			questionarioDAO.delete(questionario);
			
			questionario.getMenu().setId(Integer.valueOf(event.getComponent().getId().substring(event.getComponent().getId().indexOf("_m_")+3,event.getComponent().getId().length())));
			Integer idMenu = questionario.getMenu().getId();
			menuDAO.delete(new Menu(idMenu));
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Questionario apagado", "Questionario excluido com sucesso.");  

			addMessage(message);
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}  

	private UIComponent criaLink(Questionario questionario, int i) {
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

		param = new UIParameter();
		param.setName("questionario.titulo");
		param.setValue(questionario.getTitulo());
		link.getChildren().add(param);

		param = new UIParameter();
		param.setName("questionario.id");
		param.setValue(questionario.getId());
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
