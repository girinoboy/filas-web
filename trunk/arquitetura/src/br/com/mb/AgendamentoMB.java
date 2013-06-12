/**
 * 
 */
package br.com.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.dao.ConsultaDAO;
import br.com.dao.EspecialidadeDAO;
import br.com.dao.HorarioAtendimentoSubUnidadeDAO;
import br.com.dto.ConsultaDTO;
import br.com.dto.EspecialidadeDTO;
import br.com.dto.HorarioAtendimentoSubUnidadeDTO;
import br.com.dto.TipoConsultaDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.TipoConsultaConverter;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@ViewScoped
public class AgendamentoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date1;
	private ConsultaDTO consultaDTO;
	private TipoConsultaDTO tipoConsulta;
	private EspecialidadeDTO especialidade;
	private List<TipoConsultaDTO> listTipoConsulta;
	private List<EspecialidadeDTO> listEspecialidade;
	//HorarioAtendimentoSubUnidadeDAO horarioAtendimentoSubUnidadeDAO;
	private EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private List<UsuarioDTO> listProfissional;
	private List<HorarioAtendimentoSubUnidadeDTO> listHorarioAtendimento;
	private HorarioAtendimentoSubUnidadeDAO horarioAtendimentoSubUnidadeDAO = new HorarioAtendimentoSubUnidadeDAO();

	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public AgendamentoMB() {
		listTipoConsulta = TipoConsultaConverter.listTipoConsulta;//populaTipoConsulta();
		HorarioAtendimentoSubUnidadeDTO hasu = new HorarioAtendimentoSubUnidadeDTO();
		hasu.getUnidadeSaudeDTO().setId(1);
		hasu.setDataTermino(new Date());
		try {
			listEspecialidade = especialidadeDAO.consultarEspecialidadesUnidadeSaude(hasu);
			if(consultaDTO !=null){
				listHorarioAtendimento = horarioAtendimentoSubUnidadeDAO.consultarHorariosDisponiveis(consultaDTO);
			}
			lazyEventModel = new LazyScheduleModel() {

				@Override
				public void loadEvents(Date start, Date end) {
					clear();
					//List<ConsultaDTO> list = consultaDAO.consultarEntreDatas(start, end);
					for(ConsultaDTO consulta:consultaDAO.consultarEntreDatas(start, end)){
						event = new DefaultScheduleEvent(consulta.getPacienteDTO().getNome()+"-"+consulta.getTipoConsulta(),consulta.getDataHoraInic(),consulta.getDataHoraTerm());
						//event.setAllDay(false);
						event.setId(consulta.getId().toString());
						addEvent(event);
					}
				}
			};

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		listProfissional = new ArrayList<UsuarioDTO>();
	}


	private List<TipoConsultaDTO> populaTipoConsulta() {

		listTipoConsulta = new ArrayList<TipoConsultaDTO>();

		listTipoConsulta.add(new TipoConsultaDTO("Primeira Consulta","I"));
		listTipoConsulta.add(new TipoConsultaDTO("Retorno","R"));
		listTipoConsulta.add(new TipoConsultaDTO("Sessão","S"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico(Médico/Odontológico)","E"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico Médico","M"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Periódico Odontológico","O"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Admissional","A"));
		listTipoConsulta.add(new TipoConsultaDTO("Perícia","P"));

		return listTipoConsulta;
	}


	public void handleDateSelect(SelectEvent event) {  
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
	}

	public void addEvent(ActionEvent actionEvent) throws Exception {

		consultaDTO.setDataHoraInic(event.getStartDate());
		consultaDTO.setDataHoraTerm(event.getEndDate());
		consultaDTO.setObs(event.getTitle());

		if(event.getId() == null){
			eventModel.addEvent(event);
			lazyEventModel.addEvent(event);
		}else{
			consultaDTO.setId(Integer.valueOf(event.getId()));
			eventModel.updateEvent(event);
			lazyEventModel.updateEvent(event);
		}
		consultaDAO.save(consultaDTO);
		event = new DefaultScheduleEvent();
	}

	public void addLazyEvent(ActionEvent actionEvent) {
		if(event.getId() == null)
			lazyEventModel.addEvent(event);
		else
			lazyEventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
		event = selectEvent.getScheduleEvent();
	}

	public void onDateSelect(DateSelectEvent selectEvent) {
		event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}
	//metodo generico que envia mesagens para a tela
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * 
	 * @return
	 */
	public Date getDate1() {  
		return date1;  
	}  
	/**
	 * 
	 * @param date1
	 */
	public void setDate1(Date date1) {  
		this.date1 = date1;  
	}


	/**
	 * @return the consultaDTO
	 */
	public ConsultaDTO getConsultaDTO() {
		return consultaDTO;
	}


	/**
	 * @param consultaDTO the consultaDTO to set
	 */
	public void setConsultaDTO(ConsultaDTO consultaDTO) {
		this.consultaDTO = consultaDTO;
	}


	/**
	 * @return the listTipoConsulta
	 */
	public List<TipoConsultaDTO> getListTipoConsulta() {
		return listTipoConsulta;
	}


	/**
	 * @param listTipoConsulta the listTipoConsulta to set
	 */
	public void setListTipoConsulta(List<TipoConsultaDTO> listTipoConsulta) {
		this.listTipoConsulta = listTipoConsulta;
	}


	/**
	 * @return the listEspecialidade
	 */
	public List<EspecialidadeDTO> getListEspecialidade() {
		return listEspecialidade;
	}


	/**
	 * @param listEspecialidade the listEspecialidade to set
	 */
	public void setListEspecialidade(List<EspecialidadeDTO> listEspecialidade) {
		this.listEspecialidade = listEspecialidade;
	}


	/**
	 * @return the tipoConsulta
	 */
	public TipoConsultaDTO getTipoConsulta() {
		return tipoConsulta;
	}


	/**
	 * @param tipoConsulta the tipoConsulta to set
	 */
	public void setTipoConsulta(TipoConsultaDTO tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}


	/**
	 * @return the especialidade
	 */
	public EspecialidadeDTO getEspecialidade() {
		return especialidade;
	}


	/**
	 * @param especialidade the especialidade to set
	 */
	public void setEspecialidade(EspecialidadeDTO especialidade) {
		this.especialidade = especialidade;
	}


	/**
	 * @return the listProfissional
	 */
	public List<UsuarioDTO> getListProfissional() {
		return listProfissional;
	}


	/**
	 * @param listProfissional the listProfissional to set
	 */
	public void setListProfissional(List<UsuarioDTO> listProfissional) {
		this.listProfissional = listProfissional;
	}


	/**
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}


	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}


	/**
	 * @return the lazyEventModel
	 */
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}


	/**
	 * @param lazyEventModel the lazyEventModel to set
	 */
	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}


	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}


	/**
	 * @param event the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}


	public List<HorarioAtendimentoSubUnidadeDTO> getListHorarioAtendimento() {
		return listHorarioAtendimento;
	}


	public void setListHorarioAtendimento(
			List<HorarioAtendimentoSubUnidadeDTO> listHorarioAtendimento) {
		this.listHorarioAtendimento = listHorarioAtendimento;
	} 

}
