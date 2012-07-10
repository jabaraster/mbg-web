/**
 * 
 */
package jabara.web;

import jabara.entity.DeviceInfo;
import jabara.entity.DeviceInfoList;
import jabara.service.IDeviceInfoService;
import jabara.service.Injector;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXB;

import org.apache.commons.io.IOUtils;

/**
 * @author jabaraster
 */
public class DeviceTokenServlet extends HttpServlet {
    private static final long serialVersionUID = 528788343174425578L;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        final StringWriter writer = new StringWriter();
        final List<DeviceInfo> list = Injector.getInstance(IDeviceInfoService.class).getAll();
        JAXB.marshal(new DeviceInfoList(list), writer);

        pResp.setContentType(MediaType.TEXT_PLAIN);
        pResp.getWriter().println(new String(writer.getBuffer()));
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(final HttpServletRequest pReq, final HttpServletResponse pResp) throws ServletException, IOException {
        createDeviceInfo(pReq);
    }

    private void createDeviceInfo(final HttpServletRequest pReq) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final BufferedOutputStream buffer = new BufferedOutputStream(out);
        IOUtils.copy(pReq.getInputStream(), buffer);
        buffer.close();

        final DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setToken(out.toByteArray());
        // deviceInfo.setToken(new byte[] { 1, 2, 3, 4 });
        deviceInfo.setName("device1");
        Injector.getInstance(IDeviceInfoService.class).insert(deviceInfo);
    }
}
