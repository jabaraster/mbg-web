/**
 * 
 */
package jabara.service;

import jabara.entity.ImageInfo;

import java.io.InputStream;
import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(ImageInfoServiceImpl.class)
public interface IImageInfoService {

    /**
     * @return 全データ.
     */
    List<ImageInfo> getAll();

    /**
     * @param pIn
     */
    void insert(InputStream pIn);
}
