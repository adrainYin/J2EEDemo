package utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie findCookieByName(Cookie[] cookies , String cookieName){
        for (int i = 0; i < cookies.length; i++) {
            Cookie currCookie = cookies[i];
            String currCookieName = currCookie.getName();
            if (currCookieName.equals(cookieName)){
                return currCookie;
            }
        }
        return null;
    }
}
