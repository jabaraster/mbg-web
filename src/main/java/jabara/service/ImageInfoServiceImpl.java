/**
 * 
 */
package jabara.service;

import jabara.entity.ImageInfo;

import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author jabaraster
 */
public class ImageInfoServiceImpl extends DaoBase implements IImageInfoService {
    private static final long serialVersionUID = -4167077980061372916L;

    /**
     * @see jabara.service.IImageInfoService#getAll()
     */
    @Transactional
    public List<ImageInfo> getAll() {
        final EntityManager em = getEntityManager();
        final CriteriaQuery<ImageInfo> query = em.getCriteriaBuilder().createQuery(ImageInfo.class);
        query.from(ImageInfo.class);

        return em.createQuery(query).getResultList();
    }

    /**
     * @see jabara.service.IImageInfoService#insert(java.io.InputStream)
     */
    @Transactional
    public ImageInfo insert(final InputStream pIn) {
        final ImageInfo ii = new ImageInfo();
        ii.setImageData(pIn);

        getEntityManager().persist(ii);

        return ii;
    }

}
