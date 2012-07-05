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
public class CustomerServiceImpl extends DaoBase implements ICustomerService {
    private static final long serialVersionUID = 7410936175574342856L;

    /**
     * @see jabara.service.ICustomerService#getAll()
     */
    @Transactional
    public List<Customer> getAll() {
        final EntityManager em = getEntityManager();
        final CriteriaQuery<Customer> query = em.getCriteriaBuilder().createQuery(Customer.class);
        query.from(Customer.class);
        return em.createQuery(query).getResultList();
    }
}
