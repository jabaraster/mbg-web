package jabara;

import java.util.Calendar;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author じゃばら
 */
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1683421951887735325L;

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
    }
}
