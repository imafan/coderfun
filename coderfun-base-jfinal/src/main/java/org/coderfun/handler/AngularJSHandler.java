package org.coderfun.handler;

import com.jfinal.handler.Handler;
import com.jfinal.render.RenderFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by imafan on 2016/1/27 0027.
 */
public class AngularJSHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean[] booleans) {
        if (target.startsWith("/angular") || target.startsWith("/app")) {
//            RenderFactory.me().getFreeMarkerRender(target).setContext(httpServletRequest, httpServletResponse).render();
//            System.out.println("111111");
//            RenderFactory.me().getRender(target).setContext(httpServletRequest, httpServletResponse).render();
//            booleans[0] = true;
        }else{
            nextHandler.handle(target, httpServletRequest, httpServletResponse, booleans);
        }
    }

}
