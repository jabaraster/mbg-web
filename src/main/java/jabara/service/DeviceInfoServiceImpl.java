/**
 * 
 */
package jabara.service;

import jabara.entity.DeviceInfo;
import jabara.entity.DeviceInfo_;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author jabaraster
 */
public class DeviceInfoServiceImpl extends DaoBase implements IDeviceInfoService {
    private static final long serialVersionUID = -631943139501198296L;

    /**
     * @return
     */
    public List<DeviceInfo> getAll() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<DeviceInfo> query = builder.createQuery(DeviceInfo.class);
        final Root<DeviceInfo> root = query.from(DeviceInfo.class);
        query.orderBy(builder.asc(root.get(DeviceInfo_.name)));
        return em.createQuery(query).getResultList();
    }

    /**
     * @see jabara.service.IDeviceInfoService#insert(jabara.entity.DeviceInfo)
     */
    @Override
    @Transactional
    public void insert(final DeviceInfo pEntity) {
        this.insertCore(pEntity);
    }

}
