
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Main application.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
final public class Main extends app.Illumine.Helpers.Servlet
{
    /**
     * HTTP GET request & response.
     * @param request
     * @param response
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try
        {
            this.doActon("GET", request, response);
        } catch (JSONException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * HTTP POST request & response.
     * @param request
     * @param response
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try
        {
            this.doActon("POST", request, response);
        } catch (JSONException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
