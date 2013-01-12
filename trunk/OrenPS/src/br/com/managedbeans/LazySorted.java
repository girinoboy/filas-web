package br.com.managedbeans;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.models.UsuarioQuestionario;

public class LazySorted implements Comparator<UsuarioQuestionario> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LazySorted(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

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
    }
}
