/**
 * 
 */
package jabara.service;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.postgresql.Driver;

/**
 * @author じゃばら
 */
public abstract class DaoBase implements Serializable {
    private static final long                      serialVersionUID = -654742979492413270L;

    public static final EntityManagerFactory       _emf             = Persistence.createEntityManagerFactory("pu", createDbProperties());

    public static final ThreadLocal<EntityManager> _emHolder        = new ThreadLocal<EntityManager>();

    /**
     * @return
     */
    protected EntityManager getEntityManager() {
        final EntityManager ret = _emHolder.get();
        if (ret == null) {
            throw new IllegalStateException("current thread is not in tx.");
        }
        return ret;
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
