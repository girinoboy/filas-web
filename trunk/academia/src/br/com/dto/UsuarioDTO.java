/**
 * 
 */
package br.com.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import br.com.utility.DataUtils;

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "usuario")
public class UsuarioDTO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String senha;
	private String nome;
	private String tema;
	private String telefone;
	private String endereco;
	private String email;
	private String cpf;
	private String observacao;
	private String sexo;
	@Column(name ="data_nascimento")
	private Date dataNascimento;
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)//uma pessoa so tem um pagamento por mes
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="pagamento_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = true)
	private PagamentoDTO pagamentoDTO;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	@OneToMany(targetEntity=AnexoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY)
	private List<AnexoDTO> listAnexoDTO;
	@OneToMany(targetEntity=FrequenciaDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<FrequenciaDTO> listFrequenciaDTO;
	@OneToMany(targetEntity=PagamentoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<PagamentoDTO> listPagamentoDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "perfil_id", insertable = true, updatable = true, nullable = true)
	private PerfilDTO perfilDTO;

	/**
	 * 
	 */
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	/*
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }*/


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public AnexoDTO getAnexoDTO() {
		if(anexoDTO==null){
			anexoDTO = new AnexoDTO();
		}
		return anexoDTO;
	}


	public void setAnexoDTO(AnexoDTO anexoDTO) {
		this.anexoDTO = anexoDTO;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Integer getPagamentoVencendo() {
		if(pagamentoDTO !=null && pagamentoDTO.getDataPagamento() !=null){
			Calendar c = new GregorianCalendar();
			c.setTime(pagamentoDTO.getDataPagamento());
			c.add(Calendar.MONTH, +1);

			if(c.getTime().after(new Date())){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}

	public Integer getIdade(){

		Calendar dateOfBirth = new GregorianCalendar();
		if(dataNascimento!=null){
			dateOfBirth.setTime(dataNascimento);
		}
		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;

	}

	public Integer getContadorSemana(){
		Integer cont = 0;
		Calendar dateOfWeek = new GregorianCalendar();
		// Cria um objeto calendar com a data atual
	    GregorianCalendar todayF = new GregorianCalendar();
	    GregorianCalendar todayL = new GregorianCalendar();
	    todayF.setFirstDayOfWeek(Calendar.SUNDAY);
	    todayL.setFirstDayOfWeek(Calendar.SUNDAY);
	    
	    /* Agora é só pegar as informações que você quiser sobre o primeiro dia da semana */
	    todayF.setTime(DataUtils.toDateOnly(todayF.getTime()));
	    todayF.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    
	    /* Agora é só pegar as informações que você quiser sobre o último dia da semana */
	    todayL.setTime(DataUtils.toDateOnly(todayL.getTime()));
	    todayL.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
	    
	    for (FrequenciaDTO f : listFrequenciaDTO) {
	    	dateOfWeek.setTime(f.getDataEntrada());
	    	if(dateOfWeek.getTime().equals(todayF.getTime()) || dateOfWeek.getTime().equals(todayL.getTime())||(dateOfWeek.getTime().after(todayF.getTime()) && dateOfWeek.getTime().before(todayL.getTime()))){
	    		cont++;
	    	}
	    }

		return cont;
	}


	public List<FrequenciaDTO> getListFrequenciaDTO() {
		return listFrequenciaDTO;
	}


	public void setListFrequenciaDTO(List<FrequenciaDTO> listFrequenciaDTO) {
		this.listFrequenciaDTO = listFrequenciaDTO;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public List<AnexoDTO> getListAnexoDTO() {
		return listAnexoDTO;
	}


	public void setListAnexoDTO(List<AnexoDTO> listAnexoDTO) {
		this.listAnexoDTO = listAnexoDTO;
	}


	public PerfilDTO getPerfilDTO() {
		if(perfilDTO == null){
			perfilDTO = new PerfilDTO();
		}
		return perfilDTO;
	}


	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}


	public PagamentoDTO getPagamentoDTO() {
		if(pagamentoDTO==null){
			pagamentoDTO = new PagamentoDTO();
		}
		return pagamentoDTO;
	}


	public void setPagamentoDTO(PagamentoDTO pagamentoDTO) {
		this.pagamentoDTO = pagamentoDTO;
	}

	public List<PagamentoDTO> getListPagamentoDTO() {
		if(listPagamentoDTO==null){
			listPagamentoDTO = new ArrayList<PagamentoDTO>();
		}
		return listPagamentoDTO;
	}

	public void setListPagamentoDTO(List<PagamentoDTO> listPagamentoDTO) {
		this.listPagamentoDTO = listPagamentoDTO;
	}

}
