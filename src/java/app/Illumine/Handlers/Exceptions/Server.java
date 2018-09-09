package app.Illumine.Handlers.Exceptions;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Server error handler.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
public class Server
{
    /**
     * Invoke when route not found.
     * @param request
     * @param response
     * @throws IOException 
     */
    public void notFound404(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.getWriter().println("404 not found");
    }
}
