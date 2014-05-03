package util;

import javax.servlet.http.*;

public class CookieUtil {

	public static String getCookie(Cookie[] cookies, String cookieName)
	{
		String cookieVal = "";
		Cookie cookie;
		
		if(cookies != null)
		{
			for(int i = 0; i < cookies.length; i++)
			{
				cookie = cookies[i];
				
				if(cookieName.equals(cookie.getName()))
				{
					cookieVal = cookie.getValue();
				}
			}
		}
		
		return cookieVal;
	}
}
