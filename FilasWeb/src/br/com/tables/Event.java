package br.com.tables;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Event")
public class Event {
    private Long id;

    private String title;
    private Date date;

    public Event() {}

    @Id
    @GeneratedValue
    @Column(name="EVENT_ID")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
    @Column(name="DATE", nullable=false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}