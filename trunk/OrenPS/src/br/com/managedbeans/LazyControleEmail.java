package br.com.managedbeans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.models.Usuario;
import br.com.models.UsuarioQuestionario;

public class LazyControleEmail extends LazyDataModel<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
		List<Usuario> data = new ArrayList<Usuario>();  

		//filter  
		for(Usuario usuarioQuestionario : datasource) {  
			boolean match = true;  

			for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
				try {  
					String filterProperty = it.next();  
					String filterValue = filters.get(filterProperty); 
					String fieldValue = "";
					if(filterProperty.equals("globalFilter")){
						for (Field field : usuarioQuestionario.getClass().getDeclaredFields()) {
							field.setAccessible(true);
							fieldValue = String.valueOf(field.get(usuarioQuestionario));
							if(field.getName().equals("usuarioQuestionario")){
								String.valueOf(field.get(usuarioQuestionario)).contains(filterValue);
							}
							if(filterValue == null || fieldValue.contains(filterValue)) {  
								match = true;  
								break;
							}  
							else {  
								match = false;  
								//break;  
							}  
							
						}
					}else{
						fieldValue = String.valueOf(usuarioQuestionario.getClass().getDeclaredField("nome").get(usuarioQuestionario));
						
						if(filterValue == null || fieldValue.contains(filterValue)) {  
							match = true;  
						}  
						else {  
							match = false;  
							break;  
						}  
					}

					
				}catch(NoSuchFieldException n){
					match = true;
				}catch(Exception e) {  
					match = false;  
				}   
			}  

			if(match) {  
				data.add(usuarioQuestionario);  
			}  
		}  

		//sort  
		if(sortField != null) {
			Collections.sort(data, new LazySorted<Usuario, Serializable>(sortField, sortOrder));  
		}  

		//rowCount  
		int dataSize = data.size();  
		this.setRowCount(dataSize);  

		//paginate  
		if(dataSize > pageSize) {  
			try {  
				return data.subList(first, first + pageSize);  
			}  
			catch(IndexOutOfBoundsException e) {  
				return data.subList(first, first + (dataSize % pageSize));  
			}  
		}  
		else {  
			return data;  
		} 
		//return null;
	}

	public LazyControleEmail() {  
	}  
	
	private List<Usuario> datasource;  

	public LazyControleEmail(List<Usuario> datasource) {  
		super();
		this.datasource = datasource;
	} 
/*
	public LazyControleEmail(Object data) {  
		super();
	}  */

	@Override  
	public Usuario getRowData(String rowKey) {  
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

		List<Usuario> cars = (List<Usuario>) getWrappedData();  

		for(Usuario car : cars) {  
			if(car.getId().equals(rowKey))  
				return car;  
		}  

		return null;  
	}  

	@Override  
	public Integer getRowKey(Usuario car) {  
		return car.getId();  
	}  

}
