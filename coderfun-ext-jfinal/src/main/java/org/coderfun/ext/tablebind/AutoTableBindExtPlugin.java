/**
 * Copyright (c) 2011-2013, kidzhou 鍛ㄧ (zhouleib1412@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.coderfun.ext.tablebind;

import com.google.common.collect.Lists;


import com.jfinal.ext.plugin.tablebind.INameStyle;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jfinal.plugin.activerecord.Model;
import org.coderfun.ext.kit.ClassSearcher;
import org.coderfun.ext.kit.Reflect;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;


public class AutoTableBindExtPlugin extends ActiveRecordPlugin {

    protected final Logger log = Logger.getLogger(getClass());

    @SuppressWarnings("rawtypes")
    private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> includeJars = Lists.newArrayList();
    private boolean autoScan = true;
    private boolean includeAllJarsInLib = false;
    private List<String> scanPackages = Lists.newArrayList();
    private INameStyle nameStyle;
    private String classpath = PathKit.getRootClassPath();
    private String libDir = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + "lib";

    public AutoTableBindExtPlugin(IDataSourceProvider dataSourceProvider) {
        this(DbKit.MAIN_CONFIG_NAME, dataSourceProvider, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(String configName, IDataSourceProvider dataSourceProvider) {
        this(configName, dataSourceProvider, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(IDataSourceProvider dataSourceProvider, int transactionLevel) {
        this(DbKit.MAIN_CONFIG_NAME, dataSourceProvider, transactionLevel, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(String configName, IDataSourceProvider dataSourceProvider, int transactionLevel) {
        this(configName, dataSourceProvider, transactionLevel, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(IDataSourceProvider dataSourceProvider, INameStyle nameStyle) {
        super(DbKit.MAIN_CONFIG_NAME, dataSourceProvider);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(String configName, IDataSourceProvider dataSourceProvider, INameStyle nameStyle) {
        super(configName, dataSourceProvider);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(IDataSourceProvider dataSourceProvider, int transactionLevel, INameStyle nameStyle) {
        super(DbKit.MAIN_CONFIG_NAME, dataSourceProvider, transactionLevel);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(String configName, IDataSourceProvider dataSourceProvider, int transactionLevel,INameStyle nameStyle) {
        super(configName, dataSourceProvider, transactionLevel);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(DataSource dataSource) {
        this(DbKit.MAIN_CONFIG_NAME, dataSource, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(String configName, DataSource dataSource) {
        this(configName, dataSource, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(DataSource dataSource, int transactionLevel) {
        this(DbKit.MAIN_CONFIG_NAME, dataSource, transactionLevel, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(String configName, DataSource dataSource, int transactionLevel) {
        this(configName, dataSource, transactionLevel, SimpleNameStyles.DEFAULT);
    }

    public AutoTableBindExtPlugin(DataSource dataSource, INameStyle nameStyle) {
        super(DbKit.MAIN_CONFIG_NAME, dataSource);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(String configName, DataSource dataSource, INameStyle nameStyle) {
        super(configName, dataSource);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(DataSource dataSource, int transactionLevel, INameStyle nameStyle) {
        super(DbKit.MAIN_CONFIG_NAME, dataSource, transactionLevel);
        this.nameStyle = nameStyle;
    }

    public AutoTableBindExtPlugin(String configName, DataSource dataSource, int transactionLevel, INameStyle nameStyle) {
        super(configName, dataSource, transactionLevel);
        this.nameStyle = nameStyle;
    }

    /**
     * 添加需要扫描的包，默认为扫描所有包
     *
     * @param packages
     * @return
     */
    public AutoTableBindExtPlugin addScanPackages(String... packages) {
        for (String pkg : packages) {
            scanPackages.add(pkg);
        }
        return this;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public AutoTableBindExtPlugin addExcludeClasses(Class<? extends Model>... clazzes) {
        for (Class<? extends Model> clazz : clazzes) {
            excludeClasses.add(clazz);
        }
        return this;
    }

    @SuppressWarnings("rawtypes")
    public AutoTableBindExtPlugin addExcludeClasses(List<Class<? extends Model>> clazzes) {
        if (clazzes != null) {
            excludeClasses.addAll(clazzes);
        }
        return this;
    }

    public AutoTableBindExtPlugin addJars(List<String> jars) {
        if (jars != null) {
            includeJars.addAll(jars);
        }
        return this;
    }

    public AutoTableBindExtPlugin addJars(String... jars) {
        if (jars != null) {
            for (String jar : jars) {
                includeJars.add(jar);
            }
        }
        return this;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public boolean start() {
        List<Class<? extends Model>> modelClasses = ClassSearcher.of(Model.class).libDir(libDir).classpath(classpath)
                .scanPackages(scanPackages).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();
        TableBind tb;
        for (Class modelClass : modelClasses) {
            if (excludeClasses.contains(modelClass)) {
                continue;
            }
            tb = (TableBind) modelClass.getAnnotation(TableBind.class);
            String tableName;
            String arpConfName = Reflect.on(this).get("configName");
            if (tb == null) {
                if (!autoScan) {
                    continue;
                }
                tableName = nameStyle.name(modelClass.getSimpleName());
                this.addMapping(tableName, modelClass);
                log.debug(arpConfName + " addMapping(" + tableName + ", " + modelClass.getName() + ")");
            } else {
                String tbConfName = tb.configName();
                if (StrKit.notBlank(tbConfName) && !tbConfName.equals(arpConfName)) continue;
                tableName = tb.tableName();
                if (StrKit.notBlank(tb.pkName())) {
                    this.addMapping(tableName, tb.pkName(), modelClass);
                    log.debug(arpConfName + " addMapping(" + tableName + ", " + tb.pkName() + "," + modelClass.getName() + ")");
                } else {
                    this.addMapping(tableName, modelClass);
                    log.debug(arpConfName + " addMapping(" + tableName + ", " + modelClass.getName() + ")");
                }
            }
        }
        return super.start();
    }

    @Override
    public boolean stop() {
        return super.stop();
    }

    public AutoTableBindExtPlugin autoScan(boolean autoScan) {
        this.autoScan = autoScan;
        return this;
    }

    public AutoTableBindExtPlugin classpath(String classpath) {
        this.classpath = classpath;
        return this;
    }

    public AutoTableBindExtPlugin libDir(String libDir) {
        this.libDir = libDir;
        return this;
    }
    public AutoTableBindExtPlugin includeAllJarsInLib(boolean includeAllJarsInLib) {
        this.includeAllJarsInLib = includeAllJarsInLib;
        return this;
    }
}
