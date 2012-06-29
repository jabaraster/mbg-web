/**
 * 
 */
package jabara.web.page;

import jabara.entity.Customer;
import jabara.service.ICustomerService;
import jabara.service.IImageInfoService;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.google.inject.Inject;

/**
 * @author じゃばら
 */
public class MainPage extends WebPage {
    private static final long serialVersionUID = -2386525346155477046L;

    @Inject
    ICustomerService          customerService;

    @Inject
    IImageInfoService         imageInfoService;

    private Form<Object>      imageForm;
    private Button            submit;
    private FileUploadField   file;

    /**
     * 
     */
    @SuppressWarnings("nls")
    public MainPage() {

        this.add(getImageForm());

        this.add(new ListView<Customer>("systemProperties", this.customerService.getAll()) {
            private static final long serialVersionUID = 2808532203362435628L;

            @Override
            protected void populateItem(final ListItem<Customer> pItem) {
                pItem.add(new Label("key", String.valueOf(pItem.getModelObject().getId())));
                pItem.add(new Label("value", String.valueOf(pItem.getModelObject().getName())));
            }
        });

        this.add(new Label("dbUrl", ""));
        this.add(new Label("title", "じゃばらのお勉強"));
        this.add(new Label("month", "4"));
        this.add(new Label("weekIndex", "1"));
        this.add(new Label("percent", "40"));
        this.add(new Label("reminder", "6"));
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

    private Button getSubmit() {
        if (this.submit == null) {
            this.submit = new Button("submit") { //$NON-NLS-1$
                private static final long serialVersionUID = -2902863312599179464L;

                @SuppressWarnings("synthetic-access")
                @Override
                public void onSubmit() {
                    try {
                        MainPage.this.imageInfoService.insert(getFile().getFileUpload().getInputStream());
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return this.submit;
    }
}
