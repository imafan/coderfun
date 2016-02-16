package org.coderfun.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by imafan on 2016/2/16 0016.
 */
public class ResourceHandler {
    private String[] resourceUrls;

    public ResourceHandler(String... resourceUrls) {
        this.resourceUrls = resourceUrls;
    }

//    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
//        if(!this.checkResource(target)) {
//            this.nextHandler.handle(target, request, response, isHandled);
//        }
//    }
//
//    public boolean checkResource(String resouceUrl) {
//        if(this.resourceUrls != null && this.resourceUrls.length > 0) {
//            String[] arr$ = this.resourceUrls;
//            int len$ = arr$.length;
//
//            for(int i$ = 0; i$ < len$; ++i$) {
//                String url = arr$[i$];
//                if(PatternMatcher.match(url, resouceUrl)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
}
