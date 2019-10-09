# Java MVC with Routing

## Examples

### Route *(web/WEB-INF/config/route.json)*

```
{
    "routes": [
        {
            "url": "/",
            "type": "get",
            "action": "CmsConroller@posts"
        },
        {
            "url": "/{post}",
            "type": "get",
            "action": "CmsConroller@postDetails"
        } 
    ]
}
```

### Controller *(controllers.CmsConroller.java)*

```
public class CmsConroller
{
    public void posts(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws IOException
    {
        /**
         * This data can be fetched from database.
         */
        response.getWriter().println('{ "posts" : [{ "title" : "Test Title", "details" : "Test" }] }');
    }
    public void postDetails(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws IOException
    {
        /**
         * This data can be fetched from database as per post
         * args.get(0) is URL param (1st param). It can be ID or anything
         */
        response.getWriter().println('{ "post": "' + args.get(0) + '", "title" : "Test Title", "details" : "Test" }');
    }
}
```

Thnaks, Happy coding :)<br />
Krishna Paul <sendmail2krrish@gmail.com>
