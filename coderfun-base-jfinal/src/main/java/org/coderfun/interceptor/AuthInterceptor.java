package org.coderfun.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import org.apache.commons.lang.StringUtils;
import org.coderfun.utils.RequestUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by imafan_work on 2015/10/9 0009.
 */
public class AuthInterceptor implements Interceptor {


    public void intercept(Invocation invocation) {

        HttpServletRequest request = invocation.getController().getRequest();
        System.out.println("getContextPath:" + request.getContextPath());
        System.out.println("getServletPath:" + request.getServletPath());
        System.out.println("getRequestURI:" + request.getRequestURI());
        System.out.println("getRequestURL:" + request.getRequestURL());
        System.out.println("realPath:" + JFinal.me().getServletContext().getRealPath(""));
        System.out.println("getPackagePath:" + PathKit.getPackagePath(this));
        System.out.println("getWebRootPath:" + PathKit.getWebRootPath());
        System.out.println("getRootClassPath:" + PathKit.getRootClassPath());
        String path = request.getServletPath();
        boolean isAjax = false;

        Controller controller = invocation.getController();
        if(StringUtils.isNotBlank(path) && path.startsWith("/admin")){

            //判断是否ajax请求
            isAjax = RequestUtils.isAjaxReq(request);
            System.out.println("isAjax:" + isAjax);
            HttpSession session = invocation.getController().getSession();

        }

        invocation.invoke();
    }
}
