package org.coderfun.base;

import com.jfinal.core.Controller;

/**
 * Created by imafan_work on 2015/10/20 0020.
 */
public class BaseController extends Controller {

    public static final String FRONT_PAGE = "/ftl/front";  //前台页面
    public static final String BACKEND_PAGE = "/ftl/backend"; //后台页面
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;  //默认分页大小

    public void renderFront(String view){
        super.render(FRONT_PAGE + view);
    }

    public void renderBackend(String view){
        super.render(BACKEND_PAGE + view);
    }

}
