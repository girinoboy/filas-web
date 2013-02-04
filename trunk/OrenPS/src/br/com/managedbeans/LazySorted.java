package br.com.managedbeans;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

public class LazySorted<T, ID extends Serializable> implements Comparator<T> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    private final Class<T> oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    public Class<T> getObjectClass() {
        return this.oClass;
    }
    /*
    public LazySorted(){
    	this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }*/
    
    public LazySorted(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
/*
	public int compare(UsuarioQuestionario car1, UsuarioQuestionario car2) {
        try {
            Object value1 = UsuarioQuestionario.class.getField(this.sortField).get(car1);
            Object value2 = UsuarioQuestionario.class.getField(this.sortField).get(car2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }*/

	@Override
	public int compare(T arg0, T arg1) {
		try {
            Object value1 = oClass.getField(this.sortField).get(arg0);
            Object value2 = oClass.getField(this.sortField).get(arg1);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
	}
}
