package br.com.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.dto.AbstractDTO;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 * @param <T>
 */
@SuppressWarnings({"serial"})
public class LazyAbstractDataModel<T> extends LazyDataModel<T> implements Serializable{

	private List<T> datasource;

	public LazyAbstractDataModel(List<T> datasource) {
		this.datasource = datasource;
	}

	@Override
	public T getRowData(String rowKey) {
		for(T t : datasource) {
			if(((AbstractDTO) t).getId().equals(rowKey))  
				return t;
		}

		return null;
	}

	@Override
	public Object getRowKey(T t) {
		return ((AbstractDTO) t).getId();
	}

	@Override  
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
		List<T> data = new ArrayList<T>();

		//filter
		for(T t : datasource) {
			boolean match = true;

			for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				try {
					String filterProperty = it.next();
					String filterValue = filters.get(filterProperty);
					String fieldValue = String.valueOf(t.getClass().getField(filterProperty).get(t));

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
				data.add(t);
			}
		}

		//sort  
		if(sortField != null) {
			Collections.sort(data, new LazySorter<T>(sortField, sortOrder));
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
