/**
 * 
 */
package jabara.service;

import jabara.entity.ImageInfo;

import java.io.InputStream;

/**
 * @author jabaraster
 */
public class ImageInfoServiceImpl extends DaoBase implements IImageInfoService {
    private static final long serialVersionUID = -4167077980061372916L;

    /**
     * @see jabara.service.IImageInfoService#insert(java.io.InputStream)
     */
    @Override
    @Transactional
    public void insert(final InputStream pIn) {
        final ImageInfo ii = new ImageInfo();
        ii.setImageData(pIn);

        this.getEntityManager().persist(ii);
    }

}
