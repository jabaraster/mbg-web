/**
 * 
 */
package jabara.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author じゃばら
 */
public class MainPage extends WebPage {
    private static final long serialVersionUID = -2386525346155477046L;

    /**
     * 
     */
    public MainPage() {
        this.add(new Label("title", "じゃばらのお勉強"));
        this.add(new Label("month", "4"));
        this.add(new Label("weekIndex", "1"));
        this.add(new Label("percent", "40"));
        this.add(new Label("reminder", "6"));
    }
}