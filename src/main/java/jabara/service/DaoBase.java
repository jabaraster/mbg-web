/**
 * 
 */
package jabara.service;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author じゃばら
 */
public abstract class DaoBase implements Serializable {
    private static final long serialVersionUID = -654742979492413270L;

    /**
     * @param <T>
     * @param pOperation
     * @return
     */
    public <T> T db(final IOperation<T> pOperation) {
        Connection conn = null;
        try {
            conn = getConnection();
            return pOperation.operate(conn);

        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn);
        }
    }

    /**
     * @return
     * @throws URISyntaxException
     * @throws SQLException
     */
    protected Connection getConnection() {
        try {
            final String url = System.getenv("DATABASE_URL");
            if (url == null) {
                return getLocalConnection();
            }
            final URI dbUri = new URI(url);
            final String username = dbUri.getUserInfo().split(":")[0];
            final String password = dbUri.getUserInfo().split(":")[1];
            final String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            final Connection conn = DriverManager.getConnection(dbUrl, username, password);
            return conn;

        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        } catch (final URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param pConnection
     */
    public static void close(final Connection pConnection) {
        if (pConnection != null) {
            try {
                pConnection.close();
            } catch (final SQLException e) {
                //
            }
        }
    }

    /**
     * @param rs
     */
    public static void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (final SQLException e) {
                //
            }
        }
    }

    private static Connection getLocalConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        return DriverManager.getConnection(url, "postgres", "postgres");
    }

    /**
     * @author じゃばら
     * @param <T>
     */
    public interface IOperation<T> {
        /**
         * @param pConnection
         * @return
         * @throws SQLException
         */
        T operate(Connection pConnection) throws SQLException;
    }

}
