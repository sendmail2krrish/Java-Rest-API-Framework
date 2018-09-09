package app.Illumine.Helpers.abstracts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Pattern;

/**
 * Check route with current URL and call it's action.
 * @author Krishna Paul <sendmail2krrish@gmail.com>
 */
public class RouteParser extends Parser
{
    /**
     * Store controller name & method name
     */
    protected static String controller = "";

    /**
     * Store all route values from URL
     */
    protected static ArrayList<String> args = new ArrayList<>();

    /**
     * Loads all routes.
     *
     * @param httpType
     * @param servletPath
     * @param path
     * @throws IOException
     * @throws JSONException
     */
    protected static void load(String httpType, String servletPath, String path) throws IOException, JSONException
    {
        String routes = new String(Files.readAllBytes(Paths.get(path + RouteParser.route)), StandardCharsets.UTF_8);
        RouteParser.getAction(httpType, servletPath, routes);
    }

    /**
     * Find current route and return the action.
     *
     * @param httpType
     * @param servletPath
     * @param json
     * @throws JSONException
     */
    protected static void getAction(String httpType, String servletPath, String json) throws JSONException
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray routes = jsonObj.getJSONArray("routes");
        
        if(!"/".equals(servletPath))
        {
            servletPath = servletPath.replaceAll("/$", "");
        }

        for (int i = 0; i < routes.length(); i++)
        {
            JSONObject routeObj = new JSONObject(routes.get(i).toString());

            if (httpType.equals(routeObj.getString("type").toUpperCase()))
            {
                if (routeObj.getString("url").equals(servletPath))
                {   
                    RouteParser.controller = "controllers." + routeObj.getString("action");
                    break;
                } else if (!routeObj.getString("url").equals("/"))
                {
                    String[] routePaths = routeObj.getString("url").split("/");
                    String[] currentUri = servletPath.split("/");
                    String newUrl = "";

                    if (routePaths.length == currentUri.length)
                    {
                        for (int r = 1; r < routePaths.length; r++)
                        {
                            if (Pattern.matches("\\{([^}]*.?)\\}", routePaths[r]))
                            {
                                newUrl += "/" + currentUri[r];
                                RouteParser.args.add(currentUri[r]);
                            }
                        }
                    }
                    if (newUrl.equals(servletPath))
                    {
                        System.out.println(RouteParser.controller);
                        RouteParser.controller = "controllers." + routeObj.getString("action");
                        break;
                    } else
                    {
                        System.out.println(RouteParser.controller);
                        RouteParser.controller = "app.Illumine.Handlers.Exceptions.Server@notFound404";
                    }
                }
            }
        }

    }
}
