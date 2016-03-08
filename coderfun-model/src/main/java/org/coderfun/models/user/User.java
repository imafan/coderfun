package org.coderfun.models.user;



import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by imafan on 2016/3/1 0001.
 */
@TableBind(tableName = "t_user")
public class User extends Model<User> {
    public static final User me = new User();
}
