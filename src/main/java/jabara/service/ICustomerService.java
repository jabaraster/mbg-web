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
@ImplementedBy(CustomerService.class)
public interface ICustomerService {

    /**
     * @return
     */
    List<Customer> getAll();
}
