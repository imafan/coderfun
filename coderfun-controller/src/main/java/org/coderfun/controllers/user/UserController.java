package org.coderfun.controllers.user;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import org.coderfun.models.user.User;
import org.coderfun.services.user.UserService;

/**
 * Created by imafan on 2016/2/29 0029.
 */
@ControllerBind(controllerKey="/user")
public class UserController extends Controller{
    UserService userService = new UserService();

    public void index(){
        JSONObject returnData = new JSONObject();
        returnData.put("success",true);
        if(User.me != null){
            returnData.put("data", User.me.find("select * from t_user"));
        }else{
            User test = new User();
            returnData.put("data", test.find("select * from t_user"));
        }



        renderJson(returnData);

    }
}
