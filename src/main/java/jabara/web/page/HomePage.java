package jabara.web.page;

import jabara.service.IStorageService;
import jabara.service.Inject;

import java.util.Calendar;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author じゃばら
 */
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1683421951887735325L;

    @Inject
    IStorageService           storageService;

    private Form<Void>        form;
    private FeedbackPanel     feedback;
    private Button            tableCreator;

    /**
     * @param parameters
     */
    public HomePage(final PageParameters parameters) {
        this.add(new Label("label", new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = -1856103619976132317L;

            @Override
            public String getObject() {
                return String.valueOf(Calendar.getInstance().getTime());
            }
        }));
        this.add(getForm());
    }

    private FeedbackPanel getFeedback() {
        if (this.feedback == null) {
            this.feedback = new FeedbackPanel("feedback");
        }
        return this.feedback;
    }

    private Form<Void> getForm() {
        if (this.form == null) {
            this.form = new Form<Void>("form");
            this.form.add(getFeedback());
            this.form.add(getTableCreator());
        }
        return this.form;
    }

    private Button getTableCreator() {
        if (this.tableCreator == null) {
            this.tableCreator = new Button("tableCreator") {
                private static final long serialVersionUID = -7510564570675653434L;

                @Override
                public void onSubmit() {
                    try {
                        HomePage.this.storageService.createTable();
                    } catch (final Exception e) {
                        e.printStackTrace();
                        error(String.valueOf(e));
                    }
                }
            };
        }
        return this.tableCreator;
    }
}
