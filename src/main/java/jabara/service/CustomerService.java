/**
 * 
 */
package jabara.service;

import jabara.entity.Customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author jabaraster
 */
public class CustomerService {

    /**
     * @return
     */
    public List<Customer> getAll() {
        final EntityManager em = EntityManagerHolder.get();
        final CriteriaQuery<Customer> query = em.getCriteriaBuilder().createQuery(Customer.class);
        query.from(Customer.class);
        return em.createQuery(query).getResultList();
    }
}
