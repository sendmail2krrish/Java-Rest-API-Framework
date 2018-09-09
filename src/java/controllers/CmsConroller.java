package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Demo controller.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
public class CmsConroller
{
    public void home(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws IOException
    {
        response.getWriter().println("Home");
    }

    public void about(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws IOException
    {
        response.getWriter().println("About");
    }

    public void page(
            HttpServletRequest request,
            HttpServletResponse response,
            ArrayList args
    )
            throws IOException
    {
        response.getWriter().println(args.get(0));
    }
    
    public void pageWithPost(
            HttpServletRequest request,
            HttpServletResponse response,
            ArrayList args
    )
            throws IOException
    {
        response.getWriter().println(args.get(0) + " - " + args.get(1));;
    }
}
