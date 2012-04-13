/**
 * 
 */
package jabara.service;

import jabara.model.TableInfo;

import java.util.List;

/**
 * @author じゃばら
 */
public interface IStorageService {

    /**
     * 
     */
    void createTable();

    /**
     * @return
     */
    List<TableInfo> getTables();
}
