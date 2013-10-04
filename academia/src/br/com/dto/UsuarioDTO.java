/**
 * 
 */
package br.com.dto;

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
import javax.persistence.Table;

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
	private Double valor;
	private String sexo;
	@Column(name ="data_nascimento")
	private Date dataNascimento;
	@Column(name ="data_pagamento")
	private Date dataPagamento;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	@OneToMany(targetEntity=AnexoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY)
	private List<FrequenciaDTO> listAnexoDTO;
	@OneToMany(targetEntity=FrequenciaDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<FrequenciaDTO> listFrequenciaDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id", insertable = true, updatable = true, nullable = true)
	private PerfilDTO perfilDTO;

	/**
	 * 
	 */
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}


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


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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
		if(dataPagamento !=null){
			Calendar c = new GregorianCalendar();
			c.setTime(dataPagamento);
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


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Integer getContadorSemana(){
		Integer cont = 0;
		// Cria um objeto calendar com a data atual
		 Calendar today = Calendar.getInstance();
		 Calendar dateOfWeek = new GregorianCalendar();
		 if(!listFrequenciaDTO.isEmpty()){
			 if(listFrequenciaDTO.get(0).getDataEntrada()!=null){
				 dateOfWeek.setTime(listFrequenciaDTO.get(0).getDataEntrada());
			 }
		 }
		 int diaSemana = dateOfWeek.get(Calendar.DAY_OF_WEEK); //vai de 1-7 começando pelo domingo
		 today.add(Calendar.DAY_OF_MONTH, -diaSemana+1);

		 for (FrequenciaDTO f : listFrequenciaDTO) {

			 if(today.getTime().before(f.getDataEntrada())){
				 cont++;
			 }else{
				 break;
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


	public List<FrequenciaDTO> getListAnexoDTO() {
		return listAnexoDTO;
	}


	public void setListAnexoDTO(List<FrequenciaDTO> listAnexoDTO) {
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

}
