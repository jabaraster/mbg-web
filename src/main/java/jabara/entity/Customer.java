package jabara.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author jabaraster
 */
@Entity
public class Customer extends EntityBase<Customer> {
    private static final long serialVersionUID = -7177573599205053533L;

    /**
     * 
     */
    @Column(nullable = false, length = 100)
    protected String          name;

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
