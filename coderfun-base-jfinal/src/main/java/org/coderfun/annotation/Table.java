package org.coderfun.annotation;

import java.lang.annotation.*;

/**
 * Created by imafan_work on 2015/10/16 0016.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
     String tableName();
     String pkName() default "";
}
