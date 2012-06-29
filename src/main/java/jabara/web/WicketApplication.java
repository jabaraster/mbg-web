package jabara.web;

import jabara.service.DaoBase;
import jabara.service.Transactional;
import jabara.web.page.ImageInfoPage;
import jabara.web.page.MainPage;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<MainPage> getHomePage() {
        return MainPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        getRequestCycleSettings().setResponseRequestEncoding("UTF-8"); //$NON-NLS-1$
        getMarkupSettings().setDefaultMarkupEncoding(getRequestCycleSettings().getResponseRequestEncoding());

        mountPage("imageInfo", ImageInfoPage.class); //$NON-NLS-1$

        final Module module = new AbstractModule() {

            @Override
            protected void configure() {
                final Matcher<? super Class<?>> classMatcher = Matchers.subclassesOf(DaoBase.class);
                final Matcher<? super Method> methodMatcher = Matchers.annotatedWith(Transactional.class);
                final MethodInterceptor interceptor = new MethodInterceptor() {

                    @Override
                    public Object invoke(final MethodInvocation pInvocation) throws Throwable {
                        EntityManager em = DaoBase._emHolder.get();
                        final boolean startTxThisFrame = em == null;
                        if (em == null) {
                            em = DaoBase._emf.createEntityManager();
                            DaoBase._emHolder.set(em);
                            em.getTransaction().begin();
                        }
                        try {
                            final Object ret = pInvocation.proceed();
                            if (startTxThisFrame) {
                                em.getTransaction().commit();
                            }
                            return ret;

                        } catch (final Throwable e) {
                            if (startTxThisFrame) {
                                em.getTransaction().rollback();
                            }
                            throw e;

                        } finally {
                            if (startTxThisFrame) {
                                DaoBase._emHolder.set(null);
                            }
                        }
                    }
                };
                this.bindInterceptor(classMatcher, methodMatcher, interceptor);
            }
        };
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, module));
    }

}
