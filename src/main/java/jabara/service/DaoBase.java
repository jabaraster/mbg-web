/**
 * 
 */
package jabara.service;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author じゃばら
 */
public abstract class DaoBase implements Serializable {
    private static final long serialVersionUID = -654742979492413270L;

    /**
     * @return
     * @throws URISyntaxException
     * @throws SQLException
     */
    protected Connection getConnection() throws URISyntaxException, SQLException {
        Connection conn;
        final String url = System.getenv("DATABASE_URL");
        if (url == null) {
            return getLocalConnection();
        }
        final URI dbUri = new URI(url);
        final String username = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];
        final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        conn = DriverManager.getConnection(dbUrl, username, password);
        return conn;
    }

    private static Connection getLocalConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        return DriverManager.getConnection(url, "postgres", "postgres");
    }

}
