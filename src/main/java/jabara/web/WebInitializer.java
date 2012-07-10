/**
 * 
 */
package jabara.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.apache.jasper.servlet.JspServlet;
import org.apache.wicket.protocol.http.WicketFilter;

import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * @author jabaraster
 */
@WebListener
public class WebInitializer implements ServletContextListener {

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent pSce) {
        // 処理なし
    }

    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent pSce) {
        final ServletContext ctx = pSce.getServletContext();
        initializeWicket(ctx);
        initializeJersey(ctx);
        initializeServlet(ctx);
    }

    private FilterRegistration.Dynamic addFilter(final ServletContext pContext, final Class<? extends Filter> pFilterType) {
        return pContext.addFilter(pFilterType.getName(), pFilterType);
    }

    private ServletRegistration.Dynamic addServlet(final ServletContext pContext, final Class<? extends Servlet> pServletType) {
        return pContext.addServlet(pServletType.getName(), pServletType);
    }

    private void initializeJersey(final ServletContext pContext) {
        final ServletRegistration.Dynamic jerseyServlet = addServlet(pContext, ServletContainer.class);
        jerseyServlet.setInitParameter("com.sun.jersey.config.property.packages", "jabara.web.rest");
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE.toString());
        jerseyServlet.addMapping("/rest/*");
    }

    private void initializeServlet(final ServletContext pContext) {
        addServlet(pContext, UploadServlet.class).addMapping("/rest/upload");
        addServlet(pContext, JspServlet.class).addMapping("*.jsp");
        addServlet(pContext, DeviceTokenServlet.class).addMapping("/deviceToken");
    }

    private void initializeWicket(final ServletContext pContext) {
        final Dynamic wicketFilter = addFilter(pContext, WicketFilter.class);
        final String PATH = "/ui/*";
        wicketFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, PATH);
        wicketFilter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, PATH);
        wicketFilter.setInitParameter("applicationClassName", WicketApplication.class.getName());
    }

}
