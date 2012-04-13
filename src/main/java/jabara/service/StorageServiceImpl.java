/**
 * 
 */
package jabara.service;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author じゃばら
 */
public class StorageServiceImpl extends DaoBase implements IStorageService {
    private static final long serialVersionUID = -1168153646058503815L;

    public void createTable() {
        Connection conn = null;
        try {
            conn = getConnection();

            final String sql = "" //
                    + "\n" + "CREATE TABLE M_USER (" //
                    + "\n" + "  ID BIGINT NOT NULL " //
                    + "\n" + ", CREATED TIMESTAMP NOT NULL" //
                    + "\n" + ", UPDATED TIMESTAMP NOT NULL" //
                    + "\n" + ", USER_ID VARCHAR(20) NOT NULL UNIQUE" //
                    + "\n" + ", PASSWORD VARCHAR(32) NOT NULL" //
                    + "\n" + ", PRIMARY KEY (ID))" //
            ;
            conn.prepareStatement(sql).execute();

        } catch (final URISyntaxException e) {
            e.printStackTrace();
        } catch (final SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException e) {
                    //
                }
            }
        }
    }
}
