package org.coderfun.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by imafan on 2016/2/16 0016.
 */
public class RequestUtils {

    public static boolean isAjaxReq(HttpServletRequest request){
        return  request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest");
    }
}
