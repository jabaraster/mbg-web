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

    private static Map<String, String> createDbProperties() {
        try {
            final URI dbUri = new URI(System.getenv("DATABASE_URL"));

            final String username = dbUri.getUserInfo().split(":")[0];
            final String password = dbUri.getUserInfo().split(":")[1];
            final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + ":" + dbUri.getPort();

            final Map<String, String> ret = new HashMap<String, String>();
            ret.put("javax.persistence.jdbc.url", dbUrl);
            ret.put("javax.persistence.jdbc.user", username);
            ret.put("javax.persistence.jdbc.password", password);

            return ret;

        } catch (final URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}
