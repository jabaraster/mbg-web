/**
 * 
 */
package jabara.web.page;

import jabara.entity.ImageInfo;
import jabara.service.IImageInfoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.inject.Inject;

/**
 * @author jabaraster
 */
public class ImageInfoPage extends WebPage {
    private static final long   serialVersionUID = -8110986543463063058L;

    @Inject
    IImageInfoService           imageInfoService;

    private List<ImageInfo>     imageInfos;

    private Form<Object>        imageForm;
    private Button              submit;
    private FileUploadField     file;
    private ListView<ImageInfo> imageInfoList;

    /**
     * 
     */
    public ImageInfoPage() {
        this.add(getImageForm());
        this.add(getImageInfoList());
    }

    private List<ImageInfo> geImageInfos() {
        if (this.imageInfos == null) {
            this.imageInfos = this.imageInfoService.getAll();
        }
        return this.imageInfos;
    }

    private FileUploadField getFile() {
        if (this.file == null) {
            this.file = new FileUploadField("file"); //$NON-NLS-1$
        }
        return this.file;
    }

    private Form<Object> getImageForm() {
        if (this.imageForm == null) {
            this.imageForm = new Form<Object>("imageForm"); //$NON-NLS-1$
            this.imageForm.setMultiPart(true);
            this.imageForm.add(getSubmit());
            this.imageForm.add(getFile());
        }
        return this.imageForm;
    }

    private ListView<ImageInfo> getImageInfoList() {
        if (this.imageInfoList == null) {
            this.imageInfoList = new ListView<ImageInfo>("imageInfoList", geImageInfos()) { //$NON-NLS-1$
                private static final long serialVersionUID = -1491297374170239163L;

                @Override
                protected void populateItem(final ListItem<ImageInfo> pItem) {
                    pItem.add(new Image("image", new ResourceReference(String.valueOf(pItem.getId())) { //$NON-NLS-1$
                                private static final long serialVersionUID = -1207464172574465163L;

                                @SuppressWarnings("synthetic-access")
                                @Override
                                public IResource getResource() {
                                    return new ByteArrayResource("image/*", toByteArray(pItem.getModelObject().getImageData())); //$NON-NLS-1$
                                }
                            }));
                }
            };
        }
        return this.imageInfoList;
    }

    private Button getSubmit() {
        if (this.submit == null) {
            this.submit = new Button("submit") { //$NON-NLS-1$
                private static final long serialVersionUID = -2902863312599179464L;

                @SuppressWarnings("synthetic-access")
                @Override
                public void onSubmit() {
                    try {
                        final ImageInfo ii = ImageInfoPage.this.imageInfoService.insert(getFile().getFileUpload().getInputStream());
                        getImageInfoList().getModelObject().add(ii);
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return this.submit;
    }

    private static byte[] toByteArray(final InputStream pData) {
        try {
            final ByteArrayOutputStream mem = new ByteArrayOutputStream();
            final byte[] buf = new byte[4096];
            for (int d = pData.read(buf); d != -1; d = pData.read(buf)) {
                mem.write(buf, 0, d);
            }
            return mem.toByteArray();

        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
