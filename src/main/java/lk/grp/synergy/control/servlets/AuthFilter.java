package lk.grp.synergy.control.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by isuru on 2/11/authKey17.
 */
public class AuthFilter implements Filter {

    private ServletContext context;
    private static Logger logger = LogManager.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String localAddr = req.getLocalName();
        int localPort = req.getLocalPort();
        if(localPort!=80){
            localAddr+=(":"+localPort);
        }
        String url = "http://"+localAddr+"/oad/admin/signin";

        logger.debug("SESSION:"+session);
        logger.debug("URI:"+uri);

        if(!(uri.endsWith("html") || uri.endsWith("login") || uri.endsWith("signin") || uri.endsWith("sign-in.jsp") || uri.endsWith("css") || uri.endsWith("js"))
                && ((session != null && session.getAttribute("adminId") == null) || session==null )){
            logger.debug("URI:"+uri+", REDIRECTED:"+url);
            res.sendRedirect(url);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
