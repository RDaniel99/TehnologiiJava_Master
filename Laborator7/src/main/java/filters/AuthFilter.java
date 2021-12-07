package filters;

import beans.UserBean;
import models.User;
import utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Inject
    UserBean bean;

    private boolean alreadyRedirected;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String URI = request.getRequestURI();

        alreadyRedirected = false;

        if(URI.indexOf("/need") > 0) {

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(URI.indexOf("/login.xhtml") > 0) {

            makeLogout();
        }
        else {

            checkIfLoggedIn(request, response);
        }

        if(!alreadyRedirected && URI.indexOf("/publishDocument.xhtml") > 0) {

            checkIfCanPublish(request, response);
        }

        if(!alreadyRedirected && URI.indexOf("/createUser.xhtml") > 0) {

            checkIfAdmin(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void makeLogout() {

        SessionUtils.setUsername("");
    }

    private void checkIfAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User.UserType type = bean.getUserTypeForUserInSession();

        if (type == null || !type.isAdmin()) {

            alreadyRedirected = true;
            response.sendRedirect(request.getContextPath() + "/faces/needadmin.xhtml");
        }
    }

    private void checkIfLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(SessionUtils.getUsername() == null || SessionUtils.getUsername().isEmpty()) {

            alreadyRedirected = true;
            response.sendRedirect(request.getContextPath() + "/faces/needaccess.xhtml");
        }
    }

    private void checkIfCanPublish(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User.UserType type = bean.getUserTypeForUserInSession();

        if(type == null || !type.canPublish()) {

            alreadyRedirected = true;
            response.sendRedirect(request.getContextPath() + "/faces/needadmin.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
