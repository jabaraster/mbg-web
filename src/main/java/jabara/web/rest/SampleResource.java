/**
 * 
 */
package jabara.web.rest;

import jabara.service.CustomerServiceImpl;

import java.util.Calendar;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author jabaraster
 */
@Path("sample")
@Produces({ MediaType.APPLICATION_JSON })
public class SampleResource {

    @Inject
    CustomerServiceImpl service;

    /**
     * @return
     */
    @GET
    public String get() {
        System.out.println(this.service);
        return String.valueOf(Calendar.getInstance().getTime());
    }
}
