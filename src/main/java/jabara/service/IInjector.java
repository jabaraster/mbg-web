/**
 * 
 */
package jabara.service;

/**
 * @author じゃばら
 */
public interface IInjector {

    /**
     * @param <T>
     * @param pType
     * @return
     */
    <T> T get(Class<T> pType);
}
