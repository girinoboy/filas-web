/**
 * 
 */
package br.com.utility;

/**
 * @author marcleonio.medeiros
 *
 */
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

@SuppressWarnings("unchecked")
public class LazySorter<T> implements Comparator<T> {

    private String sortField;
    
    private SortOrder sortOrder;
    
	private final Class<T> oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(T car1, T car2) {
        try {
            Object value1 = oClass.getField(this.sortField).get(car1);
            Object value2 = oClass.getField(this.sortField).get(car2);

            int value = ((Comparable<Object>)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

