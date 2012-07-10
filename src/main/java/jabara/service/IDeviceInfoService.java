/**
 * 
 */
package jabara.service;

import jabara.entity.DeviceInfo;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(DeviceInfoServiceImpl.class)
public interface IDeviceInfoService {

    /**
     * @return
     */
    List<DeviceInfo> getAll();

    /**
     * @param pEntity
     */
    void insert(DeviceInfo pEntity);
}
