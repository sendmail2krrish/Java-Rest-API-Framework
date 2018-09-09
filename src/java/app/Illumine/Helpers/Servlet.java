/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Illumine.Helpers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Helper of HttpServlet.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
public class Servlet extends javax.servlet.http.HttpServlet
{
    /**
     * Call action of route
     *
     * @param httpType
     * @param request
     * @param response
     * @throws IOException
     * @throws org.json.JSONException
     */
    protected void doActon(
            String httpType,
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws IOException, JSONException
    {
        try
        {
            Route.call(
                    httpType,
                    request.getServletPath(),
                    getServletContext().getRealPath("/WEB-INF/config"),
                    request,
                    response
            );
        } catch (InvocationTargetException ex)
        {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
