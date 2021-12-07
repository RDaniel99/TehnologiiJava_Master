package utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionUtils.username = username;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
}
