/**
 * 
 */
package jabara.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jabaraster
 */
@Entity
@XmlRootElement(name = "DeviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceInfo extends EntityBase<DeviceInfo> {
    private static final long serialVersionUID    = 7712560713017840485L;

    private static final int  MAX_CHAR_COUNT_NAME = 20;

    /**
     * 
     */
    @Lob
    @Column(nullable = false)
    protected byte[]          token;

    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_NAME)
    protected String          name;

    /**
     * 
     */
    public DeviceInfo() {
        //
    }

    /**
     * @param pName
     * @param pToken
     */
    public DeviceInfo(final String pName, final byte[] pToken) {
        this.name = pName;
        this.token = pToken;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the token
     */
    public byte[] getToken() {
        return this.token;
    }

    /**
     * @param pName the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * @param pToken the token to set
     */
    public void setToken(final byte[] pToken) {
        this.token = pToken;
    }
}
