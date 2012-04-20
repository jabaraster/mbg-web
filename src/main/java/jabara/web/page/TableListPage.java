package jabara.web.page;

import jabara.model.TableInfo;
import jabara.service.IStorageService;
import jabara.service.Inject;

import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author じゃばら
 */
public class TableListPage extends WebPage {
    private static final long   serialVersionUID = 1683421951887735325L;

    @Inject
    IStorageService             storageService;

    private Form<Void>          form;
    private FeedbackPanel       feedback;
    private AjaxButton          tableCreator;
    private ListView<TableInfo> tables;

    /**
     * @param parameters
     */
    public TableListPage(final PageParameters parameters) {
        this.add(new Label("label", new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = -1856103619976132317L;

            @Override
            public String getObject() {
                return String.valueOf(Calendar.getInstance().getTime());
            }
        }));
        this.add(getTables());
        this.add(getForm());
    }

    private ListView<TableInfo> getTables() {
        if (this.tables == null) {
            this.tables = new ListView<TableInfo>("tables", this.storageService.getTables()) {
                private static final long serialVersionUID = 8788770579938059738L;

                @Override
                protected void populateItem(final ListItem<TableInfo> pItem) {
                    pItem.setModel(new CompoundPropertyModel<TableInfo>(pItem.getModelObject()));

                    pItem.add(new Label("name"));
                }
            };
        }
        return this.tables;
    }

    private FeedbackPanel getFeedback() {
        if (this.feedback == null) {
            this.feedback = new FeedbackPanel("feedback");
            this.feedback.setOutputMarkupId(true);
        }
        return this.feedback;
    }

    private Form<Void> getForm() {
        if (this.form == null) {
            this.form = new StatelessForm<Void>("form");
            this.form.add(getFeedback());
            this.form.add(getTableCreator());
        }
        return this.form;
    }

    private Button getTableCreator() {
        if (this.tableCreator == null) {
            this.tableCreator = new IndicatingAjaxButton("tableCreator") {
                private static final long serialVersionUID = -7510564570675653434L;

                @Override
                protected void onSubmit(final AjaxRequestTarget pTarget, final Form<?> pForm) {
                    try {
                        TableListPage.this.storageService.createTable();
                    } catch (final Exception e) {
                        e.printStackTrace();
                        error(String.valueOf(e));
                    }
                    pTarget.add(getFeedback());
                }

                @Override
                protected void onError(final AjaxRequestTarget pTarget, final Form<?> pForm) {
                    pTarget.add(getFeedback());
                }

            };
        }
        return this.tableCreator;
    }
}
