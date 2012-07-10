/**
 * 
 */
package jabara.service;

import java.io.Serializable;

import javax.persistence.EntityManager;

/**
 * @author じゃばら
 */
public abstract class DaoBase implements Serializable {
    private static final long serialVersionUID = -654742979492413270L;

    /**
     * @param pEntity
     */
    protected <E> void insertCore(final E pEntity) {
        getEntityManager().persist(pEntity);
    }

    /**
     * @return {@link EntityManager}.
     */
    public static EntityManager getEntityManager() {
        return Injector.getInstance(EntityManager.class);
    }
}
