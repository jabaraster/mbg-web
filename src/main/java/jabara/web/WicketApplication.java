package jabara.web;

import jabara.service.Inject;
import jabara.service.InjectorImpl;
import jabara.web.page.MainPage;
import jabara.web.page.TableListPage;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
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

        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding(getRequestCycleSettings().getResponseRequestEncoding());

        mountPage("tables", TableListPage.class);

        getComponentInstantiationListeners().add(new IComponentInstantiationListener() {

            @Override
            public void onInstantiation(final Component pComponent) {
                inject(pComponent);
            }
        });
    }

    private void inject(final Component pComponent) {
        for (final Field field : pComponent.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (!field.getType().isInterface()) {
                continue;
            }

            field.setAccessible(true);
            try {
                field.set(pComponent, new InjectorImpl().get(field.getType()));
            } catch (final IllegalArgumentException e) {
                e.printStackTrace();
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
