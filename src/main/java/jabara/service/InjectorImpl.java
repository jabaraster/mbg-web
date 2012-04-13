/**
 * 
 */
package jabara.service;

/**
 * @author じゃばら
 */
public class InjectorImpl implements IInjector {

    /**
     * @see jabara.service.IInjector#get(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final Class<T> pType) {
        if (!pType.isInterface()) {
            throw new IllegalStateException();
        }

        final String implementationTypeName = pType.getPackage().getName() + "." + pType.getSimpleName().substring(1)
                + "Impl";
        try {
            return (T) Class.forName(implementationTypeName).newInstance();

        } catch (final InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (final ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
