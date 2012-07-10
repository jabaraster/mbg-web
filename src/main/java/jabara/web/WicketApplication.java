package jabara.web;

import jabara.service.Injector;
import jabara.web.page.ImageInfoPage;
import jabara.web.page.MainPage;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;

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

        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, Injector.getGuiceInjector()));
    }

}
