package br.com.managedbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.models.UsuarioQuestionario;



public class LazyUsuarioQuestionarioDataModel extends LazyDataModel<UsuarioQuestionario> {

	private List<UsuarioQuestionario> datasource;  

	public LazyUsuarioQuestionarioDataModel(List<UsuarioQuestionario> datasource) {  
		this.datasource = datasource;  
	}  

	@Override  
	public UsuarioQuestionario getRowData(String rowKey) {  
		for(UsuarioQuestionario usuarioQuestionario : datasource) {  
			if(usuarioQuestionario.getEmailEnviado().equals(rowKey))  
				return usuarioQuestionario;  
		}  

		return null;  
	}  

	@Override  
	public Object getRowKey(UsuarioQuestionario usuarioQuestionario) {  
		return usuarioQuestionario.getEmailEnviado();  
	}  

	@Override  
	public List<UsuarioQuestionario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
		List<UsuarioQuestionario> data = new ArrayList<UsuarioQuestionario>();  

		//filter  
		for(UsuarioQuestionario usuarioQuestionario : datasource) {  
			boolean match = true;  

			for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
				try {  
					String filterProperty = it.next();  
					String filterValue = filters.get(filterProperty);  
					String fieldValue = String.valueOf(usuarioQuestionario.getClass().getField(filterProperty).get(usuarioQuestionario));  

					if(filterValue == null || fieldValue.startsWith(filterValue)) {  
						match = true;  
					}  
					else {  
						match = false;  
						break;  
					}  
				} catch(Exception e) {  
					match = false;  
				}   
			}  

			if(match) {  
				data.add(usuarioQuestionario);  
			}  
		}  

		//sort  
		if(sortField != null) {
			Collections.sort(data, new LazySorted(sortField, sortOrder));  
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
	}  

}
