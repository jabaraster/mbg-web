/**
 * 
 */
package jabara.service;

import jabara.model.TableInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author じゃばら
 */
public class StorageServiceImpl extends DaoBase implements IStorageService {
    private static final long serialVersionUID = -1168153646058503815L;

    /**
     * @see jabara.service.IStorageService#createTable()
     */
    @Override
    public void createTable() {
        this.db(new IOperation<Void>() {

            @Override
            public Void operate(final Connection pConnection) throws SQLException {
                final String sql = "" //
                        + "\n" + "CREATE TABLE M_USER (" //
                        + "\n" + "  ID BIGINT NOT NULL " //
                        + "\n" + ", CREATED TIMESTAMP NOT NULL" //
                        + "\n" + ", UPDATED TIMESTAMP NOT NULL" //
                        + "\n" + ", USER_ID VARCHAR(20) NOT NULL UNIQUE" //
                        + "\n" + ", PASSWORD VARCHAR(32) NOT NULL" //
                        + "\n" + ", PRIMARY KEY (ID))" //
                ;
                pConnection.prepareStatement(sql).execute();
                return null;
            }
        });
    }

    /**
     * @see jabara.service.IStorageService#getTables()
     */
    @Override
    public List<TableInfo> getTables() {
        return this.db(new IOperation<List<TableInfo>>() {

            @Override
            public List<TableInfo> operate(final Connection pConnection) throws SQLException {
                ResultSet rs = null;
                try {
                    rs = pConnection.getMetaData().getTables(null, null, null, new String[] { "TABLE" });
                    final List<TableInfo> ret = new ArrayList<TableInfo>();
                    while (rs.next()) {
                        final TableInfo table = new TableInfo();
                        table.setName(rs.getString("TABLE_NAME"));
                        ret.add(table);
                    }
                    return ret;

                } finally {
                    close(rs);
                }
            }
        });
    }
}
