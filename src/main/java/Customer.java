import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 */

/**
 * @author jabaraster
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = -7177573599205053533L;

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long            id;

    /**
     * 
     */
    @Column(nullable = false, length = 100)
    protected String          name;

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pName the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
    }
}
