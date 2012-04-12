package jabara;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

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
                try {
                    final URI dbUri = new URI(System.getenv("DATABASE_URL"));

                    final String username = dbUri.getUserInfo().split(":")[0];
                    final String password = dbUri.getUserInfo().split(":")[1];
                    final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
                    final Connection conn = DriverManager.getConnection(dbUrl, username, password);
                    final String ret = String.valueOf(conn);
                    conn.close();
                    return ret;

                } catch (final Exception e) {
                    return e.getMessage();
                }
            }
        }));
    }
}
