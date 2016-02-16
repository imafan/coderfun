package org.coderfun.base;

import cn.dreampie.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by imafan_work on 2015/10/16 0016.
 */
public class BaseService<T extends Model> {

    public String tableName;
    protected Class<T> entityClass;
    protected T obj;



    public BaseService(){
        entityClass =  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        TableBind table = entityClass.getAnnotation(TableBind.class);
        if (table != null && StringUtils.isNotBlank(table.tableName()))
        {
            tableName = table.tableName();
        }
        else
        {
            tableName = entityClass.getSimpleName();
        }
        System.out.println("tableName:" + tableName);
    }

/*    private static class SingletonHolder{
        private static final BaseService INSTANCE = new BaseService();
    }

    public static final BaseService getInstance(){
        return SingletonHolder.INSTANCE;
    }*/

    public List<T> findAll(){
        try{
            return (List<T>)getEntityInstance().find("select * from " + wrap(tableName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Page<T> findByPage(int page, int pageSize){
        try{
            return (Page<T>)getEntityInstance().paginate(page,pageSize,"select *","from " + wrap(tableName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public T findById(Serializable id){
        try{
           return (T)getEntityInstance().findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private T getEntityInstance() throws Exception{
        if(obj == null){
            obj = entityClass.newInstance();
        }

        return obj;
    }

    private String wrap(String name) {
        return "`" + name + "`";
    }

    public String getTableName(){
        return wrap(tableName);
    }
}
