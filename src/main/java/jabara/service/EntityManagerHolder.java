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

import org.postgresql.Driver;

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
            final String u = System.getenv("DATABASE_URL");
            if (u == null) {
                return createDbProperties2();
            }
            final URI dbUri = new URI(u);

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

        ret.put("javax.persistence.driver", Driver.class.getName());
        ret.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/postgres");
        ret.put("javax.persistence.jdbc.user", "jabaraster");
        ret.put("javax.persistence.jdbc.password", "w9tau9Em");

        return ret;
    }
}
