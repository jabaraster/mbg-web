/**
 * 
 */
package jabara.web.page;

import jabara.entity.Customer;
import jabara.service.ICustomerService;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
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

    /**
     * 
     */
    public MainPage() {

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
}
