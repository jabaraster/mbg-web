/**
 * 
 */
package jabara.service;

import jabara.entity.Customer;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(CustomerServiceImpl.class)
public interface ICustomerService {

    /**
     * @return 全データ.
     */
    List<Customer> getAll();
}
