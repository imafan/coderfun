package org.coderfun.controllers.user;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import org.coderfun.services.user.UserService;

/**
 * Created by imafan on 2016/2/29 0029.
 */
public class UserController extends Controller{
    UserService userService = new UserService();

    public void index(){
        JSONObject returnData = new JSONObject();
        returnData.put("success",true);
        returnData.put("data",userService.findAll());


        renderJson(returnData);

    }
}
