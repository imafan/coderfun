package org.coderfun.base;

import cn.dreampie.quartz.QuartzPlugin;
import cn.dreampie.tablebind.SimpleNameStyles;
import cn.dreampie.tablebind.TableBindPlugin;
import cn.dreampie.web.handler.ResourceHandler;
import cn.dreampie.web.handler.xss.AttackHandler;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import org.coderfun.handler.AngularJSHandler;
import org.coderfun.interceptor.AuthInterceptor;


/**
 * Created by imafan_work on 2015/10/8 0008.
 */
public class BaseConfig extends JFinalConfig {
    private static final Logger LOG = Logger.getLogger(BaseConfig.class);

    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setEncoding("utf-8");
    }

    public void configRoute(Routes me) {

    }

    public void configPlugin(Plugins me) {
        loadPropertyFile("config.properties");

//        C3p0Plugin cp = new C3p0Plugin(getProperty("jdbc.url"),getProperty("jdbc.username"), getProperty("jdbc.password"));
//        me.add(cp);

        DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
        // StatFilter提供JDBC层的统计信息
        dp.addFilter(new StatFilter());
        // WallFilter的功能是防御SQL注入攻击
        WallFilter wallDefault = new WallFilter();
        wallDefault.setDbType("mysql");
        dp.addFilter(wallDefault);
        me.add(dp);


        /**
         * 配置扫描model
         */
        //Model自动绑定表插件
        TableBindPlugin tableBindDefault = new TableBindPlugin(dp, SimpleNameStyles.LOWER);
        tableBindDefault.setContainerFactory(new CaseInsensitiveContainerFactory(true)); //忽略字段大小写
        //排除或者引入包
        //tableBindDefault.addExcludePaths("cn.dreampie.function.shop");
        //tableBindDefault.addIncludePaths("cn.dreampie.function.default");
        tableBindDefault.setShowSql(getPropertyToBoolean("devMode", false));
        //非mysql的数据库方言
        //tableBindDefault.setDialect(new AnsiSqlDialect());
        me.add(tableBindDefault);

//        me.add(new EhCachePlugin());

        me.add(new QuartzPlugin());

        /*   // 用于缓存bbs模块的redis服务
        RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost");
        me.add(bbsRedis);
        // 用于缓存news模块的redis服务
        RedisPlugin newsRedis = new RedisPlugin("news", "192.168.3.9");
        me.add(newsRedis);*/
    }

    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new AuthInterceptor());
        me.add(new SessionInViewInterceptor());
    }

    public void configHandler(Handlers me) {
        me.add(new DruidStatViewHandler("/druid"));
//        me.add(new AngularJSHandler());
//        me.add(new UrlSkipHandler(".+\\.\\w{1,4}", false));
        me.add(new ResourceHandler("/javascript/**", "/images/**", "/css/**", "/lib/**", "/**/*.html"));
        //防xss攻击
        me.add(new AttackHandler());
    }

    @Override
    public void afterJFinalStart() {

        //定义所有页面公用的属性
//        try {
//            System.out.println("url:" + PropKit.use("config.properties").get("base.url"));
//            FreeMarkerRender.getConfiguration().setSharedVariable("base", PropKit.use("config.properties").get("base.url"));
//        } catch (TemplateModelException e) {
//            LOG.error("全局参数设置失败:" + e.getMessage());
//            e.printStackTrace();
//        }
    }
}
