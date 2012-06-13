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
public class CustomerService extends DaoBase implements ICustomerService {
    private static final long serialVersionUID = 7410936175574342856L;

    /**
     * @return
     */
    @Override
    @Transactional
    public List<Customer> getAll() {
        final EntityManager em = this.getEntityManager();
        final CriteriaQuery<Customer> query = em.getCriteriaBuilder().createQuery(Customer.class);
        query.from(Customer.class);
        return em.createQuery(query).getResultList();
    }
}
