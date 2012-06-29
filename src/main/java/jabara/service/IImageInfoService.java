/**
 * 
 */
package jabara.service;

import java.io.InputStream;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(ImageInfoServiceImpl.class)
public interface IImageInfoService {

    /**
     * @param pIn
     */
    void insert(InputStream pIn);
}
