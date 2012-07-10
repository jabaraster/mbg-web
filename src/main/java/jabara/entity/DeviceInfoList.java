/**
 * 
 */
package jabara.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jabaraster
 */
@XmlRootElement(name = "DeviceInfoList")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceInfoList {

    @XmlElement(name = "DeviceInfo")
    private List<DeviceInfo> list;

    /**
     * 
     */
    public DeviceInfoList() {
        //
    }

    /**
     * @param pList
     */
    public DeviceInfoList(final List<DeviceInfo> pList) {
        this.list = pList;
    }

    /**
     * @return the list
     */
    public List<DeviceInfo> getList() {
        return this.list;
    }

    /**
     * @param pList the list to set
     */
    public void setList(final List<DeviceInfo> pList) {
        this.list = pList;
    }
}
