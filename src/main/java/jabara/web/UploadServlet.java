package jabara.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jabaraster
 */
@WebServlet(urlPatterns = { "/rest/upload" })
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = -5923346761998404622L;

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException, IOException {
        forwardToUploadPage(pRequest, pResponse);
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException, IOException {
        pRequest.setAttribute("message", pRequest.getPart("text")); //$NON-NLS-1$//$NON-NLS-2$
        forwardToUploadPage(pRequest, pResponse);
    }

    private static void forwardToUploadPage(final HttpServletRequest pRequest, final HttpServletResponse pResponse) throws ServletException,
            IOException {
        pRequest.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(pRequest, pResponse); //$NON-NLS-1$
    }
}
