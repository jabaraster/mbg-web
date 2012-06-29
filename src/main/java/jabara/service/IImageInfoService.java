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
     * @return INSERTした画像情報.
     */
    ImageInfo insert(InputStream pIn);
}
