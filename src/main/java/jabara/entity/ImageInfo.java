/**
 * 
 */
package jabara.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.io.IOUtils;

/**
 * @author jabaraster
 */
@Entity
public class ImageInfo extends EntityBase<ImageInfo> {
    private static final long serialVersionUID = 8058054358608045275L;

    /**
     * 
     */
    @Column(nullable = false)
    @Lob
    protected byte[]          imageData;

    /**
     * @return 画像情報.
     */
    public InputStream getImageData() {
        try {
            return new GZIPInputStream(new ByteArrayInputStream(this.imageData));
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param pData
     */
    public void setImageData(final InputStream pData) {
        try {
            final ByteArrayOutputStream mem = new ByteArrayOutputStream();
            final GZIPOutputStream zipOut = new GZIPOutputStream(mem);
            IOUtils.copy(pData, zipOut);
            zipOut.close();
            this.imageData = mem.toByteArray();

        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
