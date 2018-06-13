package com.mmall.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设定注解范围:
 * 		ElementType.METHOD:作用与方法上
 * 		ElementType.TYPE:作用于类和接口上
 * 		ElementType.ANNOTATION_TYPE:作用于注解上
 * 		ElementType.CONSTRUCTOR:作用于构造方法上
 * 		ElementType.FIELD:作用于域上
 * 		ElementType.LOCAL_VARIABLE:作用于局部变量
 * 		ElementType.PACKAGE:用于记录java文件的package信息
 * 		ElementType.PARAMETER:作用于参数上
 *
 * 		RetentionPolicy.SOURCE:被编译器忽略
 * 		RetentionPolicy.CLASS:注解会包保留在Class文件中,但在运行时会被JVM忽略,是默认的
 * 		RetentionPolicy.RUNTIME:保留至运行时。所以我们可以通过反射去获取注解信息。
 */
//用来标记线程安全的类
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
	String value() default "";
}
