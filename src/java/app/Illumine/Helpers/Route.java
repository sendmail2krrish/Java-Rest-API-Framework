package app.Illumine.Helpers;

import app.Illumine.Helpers.abstracts.RouteParser;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Route helper.
 * Check type and call route and it's action controller & method.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
final public class Route extends RouteParser
{
    /**
     * Store instance of called route controller.
     */
    private static Object instance;

    /**
     * Call method of controller class.
     * @param httpType
     * @param servletPath
     * @param path
     * @param request
     * @param response
     * @throws InvocationTargetException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public static void call(
            String httpType,
            String servletPath,
            String path,
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws InvocationTargetException, IOException, JSONException
    {
        Route.load(httpType, servletPath, path);
        Route.createInstance(Route.controller.split("@")[0]);
        Route.assign(Route.controller.split("@")[1], request, response, Route.args);
    }

    /**
     * Create instance of controller class.
     * @param controller
     */
    private static void createInstance(String controller)
    {
        try
        {
            Class controllerClass;
            controllerClass = Class.forName(controller);
            Route.instance = controllerClass.newInstance();
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException e)
        {
        }
    }

    /**
     * Invoke the called method of controller class.
     * @param method
     * @param request
     * @param response
     * @throws InvocationTargetException
     */
    private static void assign(
            String method,
            HttpServletRequest request,
            HttpServletResponse response,
            ArrayList args
    )
            throws InvocationTargetException, IOException
    {
        Class[] argsClass;
        Object[] argsObject;

        try
        {
            if (args.isEmpty())
            {
                argsClass = new Class[]
                {
                    HttpServletRequest.class,
                    HttpServletResponse.class
                };
                argsObject = new Object[]
                {
                    request,
                    response
                };

            }
            else
            {
                argsClass = new Class[]
                {
                    HttpServletRequest.class,
                    HttpServletResponse.class,
                    ArrayList.class
                };
                argsObject = new Object[]
                {
                    request,
                    response,
                    args
                };
            }
            Route.instance.getClass().getMethod(method, argsClass).invoke(Route.instance, argsObject);
            args.clear();
        } catch (IllegalAccessException
                | IllegalArgumentException
                | NoSuchMethodException
                | SecurityException
                | InvocationTargetException e)
        {
            response.getWriter().println(e);
        }
    }
}
