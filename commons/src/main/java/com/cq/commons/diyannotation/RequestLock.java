package com.cq.commons.diyannotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description 用于防止前端重复提交导致的错误
 * key分隔符是用来将多个参数合并在一起的，比如userName是张三，userPhone是123456，
 * 那么完整的key就是"张三&123456"，最后再加上redis锁前缀，就组成了一个唯一key。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLock {
    /**
     * redis锁前缀
     *
     * @return 默认为空，但不可为空
     */
    String prefix() default "";

    /**
     * redis锁过期时间
     *
     * @return 默认2秒
     */
    int expire() default 2;

    /**
     * redis锁过期时间单位
     *
     * @return 默认单位为秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * redis  key分隔符
     *
     * @return 分隔符
     */
    String delimiter() default "&";
}
