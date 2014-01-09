package br.com.utility;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		try {
			String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
			Calendar c = Calendar.getInstance();
			c.set(2016, Calendar.NOVEMBER, 8);
			System.out.println(pagina);
			if(c.getTime().before(new Date()) && !pagina.equals("/404.xhtml")){
				//FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
			    FacesContext.getCurrentInstance().getExternalContext().redirect("404.xhtml");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
