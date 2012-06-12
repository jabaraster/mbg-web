/**
 * 
 */
package jabara.web.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * @author じゃばら
 */
public class MainPage extends WebPage {
    private static final long serialVersionUID = -2386525346155477046L;

    /**
     * 
     */
    public MainPage() {

        final Properties props = System.getProperties();

        System.out.println(props);

        final List<Map.Entry<Object, Object>> l = new ArrayList<Map.Entry<Object, Object>>(props.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<Object, Object>>() {

            @Override
            public int compare(final Entry<Object, Object> o0, final Entry<Object, Object> o1) {
                return String.valueOf(o0.getKey()).compareTo(String.valueOf(o1.getKey()));
            }
        });

        this.add(new ListView<Map.Entry<Object, Object>>("systemProperties", l) {
            private static final long serialVersionUID = 2808532203362435628L;

            @Override
            protected void populateItem(final ListItem<Entry<Object, Object>> pItem) {
                pItem.add(new Label("key", String.valueOf(pItem.getModelObject().getKey())));
                pItem.add(new Label("value", String.valueOf(pItem.getModelObject().getValue())));
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
