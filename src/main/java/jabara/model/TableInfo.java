/**
 * 
 */
package jabara.model;

import java.io.Serializable;

/**
 * @author じゃばら
 */
public class TableInfo implements Serializable {
    private static final long serialVersionUID = -5319331484941192381L;

    private String            name;

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pName nameを設定.
     */
    public void setName(final String pName) {
        this.name = pName;
    }

}
