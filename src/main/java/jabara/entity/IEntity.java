/**
 * 
 */
package jabara.entity;

import java.util.Date;

/**
 * @author jabaraster
 */
public interface IEntity {

    /**
     * @return
     */
    Date getCreated();

    /**
     * @return
     */
    Long getId();

    /**
     * @return
     */
    Date getUpdated();
}
