/**
 * 
 */
package jabara.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author jabaraster
 */
public class EntityManagerHolder {

    private static EntityManagerFactory _emf;
    static {
        _emf = Persistence.createEntityManagerFactory("pu", createDbProperties());
    }

    /**
     * @return
     */
    public static EntityManager get() {
        return _emf.createEntityManager();
    }

    public static void main(final String[] args) {
        System.out.println(get());
    }

    private static Map<String, String> createDbProperties() {
        try {
            final URI dbUri = new URI(getDbUrl());

            final String username = dbUri.getUserInfo().split(":")[0];
            final String password = dbUri.getUserInfo().split(":")[1];
            final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + ":" + dbUri.getPort();

            final Map<String, String> ret = new HashMap<String, String>();
            ret.put("javax.persistence.driver", "org.postgres.Driver");
            ret.put("javax.persistence.jdbc.url", dbUrl);
            ret.put("javax.persistence.jdbc.user", username);
            ret.put("javax.persistence.jdbc.password", password);

            return ret;

        } catch (final URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Map<String, String> createDbProperties2() {
        final Map<String, String> ret = new HashMap<String, String>();

        ret.put("javax.persistence.driver", "org.apache.derby.jdbc.ClientDriver");
        ret.put("javax.persistence.jdbc.url", "jdbc:derby://localhost/app;create=true");
        ret.put("javax.persistence.jdbc.user", "APP");
        ret.put("javax.persistence.jdbc.password", "APP");

        return ret;
    }

    private static String getDbUrl() {
        return System.getenv("DATABASE_URL");
    }
}
