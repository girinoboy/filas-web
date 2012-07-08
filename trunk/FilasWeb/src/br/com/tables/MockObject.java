package br.com.tables;
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
 
@Entity
@Table(name = "mock_objects")
public class MockObject implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
 
    @Version
    @Column(name = "version")
    private int version = 0;
 
    public Long getId()
    {
        return id;
    }
 
    public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion()
    {
        return version;
    }
}